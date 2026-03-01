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
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.SearchVacanciesInteractor
import ru.practicum.android.diploma.presentation.model.VacanciesState
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

@OptIn(FlowPreview::class)
class SearchViewModel(private val searchVacanciesInteractor: SearchVacanciesInteractor) : ViewModel() {
    private val _state = MutableStateFlow<VacanciesState>(VacanciesState.Empty)
    val state: StateFlow<VacanciesState> = _state.asStateFlow()
    private var _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    init {
        searchText.debounce(SEARCH_DEBOUNCE_DELAY) // пауза
            .distinctUntilChanged() // ограничиваем если есть новый поиск
            .onEach { text -> searchVacancies(text) }
            .launchIn(viewModelScope)
    }

    fun searchVacancies(text: String) {
        if (text.isBlank()) return

        _state.value = VacanciesState.Loading

        viewModelScope.launch {
            val options = hashMapOf<String, String>().apply {
                put("text", text)
            }

            searchVacanciesInteractor.searchVacancies(options)
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            if (resource.data != null) {
                                if (resource.data.found != 0) {
                                    _state.value = VacanciesState.Content(resource.data)
                                } else {
                                    _state.value = VacanciesState.NotFound
                                }
                            }
                        }

                        is Resource.Error -> {
                            when (resource.error) {
                                NetworkResponseStatus.NO_INTERNET -> _state.value = VacanciesState.NoInternet
                                NetworkResponseStatus.SERVER_ERROR -> _state.value = VacanciesState.ServerError
                            }

                        }
                    }
                }
        }
    }

    fun searchDebounce(changedText: String) {
        _searchText.value = changedText
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
}
