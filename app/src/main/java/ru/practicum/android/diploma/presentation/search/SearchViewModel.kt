package ru.practicum.android.diploma.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.SearchVacanciesInteractor
import ru.practicum.android.diploma.domain.models.SearchParams
import ru.practicum.android.diploma.domain.models.VacancyShortResponse
import ru.practicum.android.diploma.presentation.model.ToastState
import ru.practicum.android.diploma.presentation.model.VacanciesState
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val searchVacanciesInteractor: SearchVacanciesInteractor,
) : ViewModel() {
    private val _searchParams = MutableStateFlow(SearchParams(text = "", page = FIRST_PAGE_INDEX))
    val searchParams: StateFlow<SearchParams> = _searchParams.asStateFlow()
    private var lastSuccesResult = VacancyShortResponse.Empty
    private val _state = MutableStateFlow<VacanciesState>(VacanciesState.Empty)
    val state: StateFlow<VacanciesState> = _state.asStateFlow()
    private val _isSearchInProgress = MutableStateFlow(false)
    val isSearchInProgress: StateFlow<Boolean> = _isSearchInProgress.asStateFlow()
    private val _query = MutableStateFlow<String>("")
    val query: StateFlow<String> = _query.asStateFlow()
    private val _toastState = MutableStateFlow<ToastState>(ToastState.NoProblem)
    val toastState: StateFlow<ToastState> = _toastState.asStateFlow()

    init {
        searchParams.debounce(SEARCH_DEBOUNCE_DELAY) // пауза
            .distinctUntilChanged() // ограничиваем если есть новый поиск
            .onEach { searchParams -> searchVacancies(searchParams.text, searchParams.page) }
            .launchIn(viewModelScope)
    }

    fun clearToast() {
        _toastState.value = ToastState.NoProblem
    }

    fun searchVacancies(text: String, page: Int) {
        if (text.isBlank()) {
            lastSuccesResult = VacancyShortResponse.Empty
            _state.value = VacanciesState.Empty
            return
        }

        _isSearchInProgress.value = true

        if (isFirstSearch()) {
            lastSuccesResult = VacancyShortResponse.Empty
            _state.value = VacanciesState.Loading
        } else {
            _state.value = VacanciesState.Content(lastSuccesResult, inProgress = true)
        }

        viewModelScope.launch {
            val options = hashMapOf<String, String>().apply {
                put("text", text)
                put("page", page.toString())
            }

            searchVacanciesInteractor.searchVacancies(options)
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            handleSuccess(resource.data)
                        }

                        is Resource.Error -> {
                            handleError(resource.error)
                        }
                    }

                    _isSearchInProgress.value = false
                }
        }
    }

    private fun handleSuccess(data: VacancyShortResponse?) {
        when {
            data == null -> _state.value = VacanciesState.NotFound
            data.found == 0 -> _state.value = VacanciesState.NotFound
            else -> {
                Log.d("SearchViewModel", "Found page ${data.page}/${data.found}")
                lastSuccesResult = VacancyShortResponse(
                    found = data.found,
                    pages = data.pages,
                    page = data.page,
                    items = lastSuccesResult.items + data.items
                )

                _state.value = VacanciesState.Content(lastSuccesResult)
            }
        }
    }

    private fun handleError(errorCode: Int?) {
        if (isFirstSearch()) {
            _state.value = when (errorCode) {
                NetworkResponseStatus.NO_INTERNET -> VacanciesState.NoInternet
                NetworkResponseStatus.SERVER_ERROR -> VacanciesState.ServerError
                else -> VacanciesState.ServerError // или другая ошибка по умолчанию
            }
        } else {
            when (errorCode) {
                NetworkResponseStatus.NO_INTERNET -> {
                    _toastState.value = ToastState.NoInternet
                    _state.value = VacanciesState.Content(lastSuccesResult)
                }

                NetworkResponseStatus.SERVER_ERROR -> {
                    _toastState.value = ToastState.ServerError
                    VacanciesState.Content(lastSuccesResult)
                }

                else -> { // или другая ошибка по умолчанию
                    _toastState.value = ToastState.ServerError
                    VacanciesState.Content(lastSuccesResult)
                }
            }
        }
    }

    fun onSearchTextDebounce(text: String) {
        _query.value = text
        _searchParams.update { it.copy(text = text, page = FIRST_PAGE_INDEX) }
    }

    fun clearSearchQuery() {
        _query.value = ""
        lastSuccesResult = VacancyShortResponse.Empty
        _state.value = VacanciesState.Empty
    }

    fun onLoadNextPage() {
        viewModelScope.launch {
            searchVacancies(searchParams.value.text, lastSuccesResult.page + PAGE_INCREMENT)
        }
    }

    private fun isFirstSearch(): Boolean {
        return lastSuccesResult.page <= FIRST_PAGE_INDEX
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private const val FIRST_PAGE_INDEX = 1
        private const val PAGE_INCREMENT = 1
    }
}
