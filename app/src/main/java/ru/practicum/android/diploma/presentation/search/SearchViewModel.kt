package ru.practicum.android.diploma.presentation.search

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
import ru.practicum.android.diploma.presentation.model.VacanciesState
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource
import java.util.concurrent.atomic.AtomicInteger

@OptIn(FlowPreview::class)
class SearchViewModel(private val searchVacanciesInteractor: SearchVacanciesInteractor) : ViewModel() {
    private var currentPage = AtomicInteger(FIRST_PAGE_INDEX)
    private var lastSuccesResult = VacancyShortResponse.Empty

    private val _state = MutableStateFlow<VacanciesState>(VacanciesState.Empty)
    val state: StateFlow<VacanciesState> = _state.asStateFlow()
    private val _searchParams = MutableStateFlow(SearchParams(text = "", page = 0))
    val searchParams: StateFlow<SearchParams> = _searchParams.asStateFlow()
    private val _isSearchInProgress = MutableStateFlow(false)
    val isSearchInProgressFlow: StateFlow<Boolean> = _isSearchInProgress.asStateFlow()

    init {
        searchParams.debounce(SEARCH_DEBOUNCE_DELAY) // пауза
            .distinctUntilChanged() // ограничиваем если есть новый поиск
            .onEach { searchParams -> searchVacancies(searchParams.text, searchParams.page) }
            .launchIn(viewModelScope)
    }

    fun searchVacancies(text: String, page: Int) {
        if (text.isBlank()) {
            lastSuccesResult = VacancyShortResponse.Empty
            _state.value = VacanciesState.Empty
            return
        }

        _isSearchInProgress.value = true

        if (page == 0) {
            lastSuccesResult = VacancyShortResponse.Empty
            _state.value = VacanciesState.Loading
        } else {
            _state.value = VacanciesState.Content(lastSuccesResult)
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
                currentPage.incrementAndGet() // увеличиваем счетчик страниц, только когда что-то нашли
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
        _state.value = when (errorCode) {
            NetworkResponseStatus.NO_INTERNET -> VacanciesState.NoInternet
            NetworkResponseStatus.SERVER_ERROR -> VacanciesState.ServerError
            else -> VacanciesState.ServerError // или другая ошибка по умолчанию
        }
    }

    fun onSearchTextDebounce(text: String) {
        currentPage.set(FIRST_PAGE_INDEX)
        _searchParams.update { it.copy(text = text, page = currentPage.get()) }
    }

    fun onLoadNextPage() {
        _searchParams.update { it.copy(text = it.text, page = currentPage.get() + PAGE_INCREMENT) }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private const val FIRST_PAGE_INDEX = 1
        private const val PAGE_INCREMENT = 1
    }
}
