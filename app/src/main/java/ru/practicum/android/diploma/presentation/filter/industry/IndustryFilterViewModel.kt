package ru.practicum.android.diploma.presentation.filter.industry

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
import ru.practicum.android.diploma.domain.api.ChangeIndustryInteractor
import ru.practicum.android.diploma.domain.api.IndustryInteractor
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.models.IndustryResponse
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

@OptIn(FlowPreview::class)
class IndustryFilterViewModel(
    private val industryInteractor: IndustryInteractor,
    private val changeIndustryInteractor: ChangeIndustryInteractor,
) : ViewModel() {
    private val _filterState = MutableStateFlow<IndustryFilterState?>(null)
    val filterState: StateFlow<IndustryFilterState?> = _filterState.asStateFlow()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    var allIndustries = listOf<Industry>()
    val selectedIndustry: StateFlow<Industry?> = changeIndustryInteractor.selectedIndustryFlow

    fun selectIndustry(industry: Industry?) {
        changeIndustryInteractor.selectIndustry(industry)
    }

    fun applySelectedIndustry() {
        changeIndustryInteractor.applySelectedIndustry()
    }

    fun clearSelectedIndustry() {
        changeIndustryInteractor.clearSelectedIndustry()
    }

    init {
        viewModelScope.launch {
            _filterState.value = IndustryFilterState.Loading
            industryInteractor.getIndustries()
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            handleSuccess(resource.data)
                        }

                        is Resource.Error -> {
                            handleError(resource.error)
                        }
                    }
                }
        }

        viewModelScope.launch {
            query.debounce(DEBOUNCE_DELAY) // пауза
                .distinctUntilChanged() // ограничиваем если есть новый поиск
                .onEach {
                    if (_filterState.value is IndustryFilterState.Content) {
                        _filterState.value = IndustryFilterState.Content(
                            getFilteredIndustries(query.value)
                        )
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun getFilteredIndustries(industryQueryString: String): List<Industry> {
        val trimmedQuery = industryQueryString.trim()
        return if (trimmedQuery.isEmpty()) {
            allIndustries
        } else {
            return allIndustries.filter { it.name.contains(query.value.trim(), ignoreCase = true) }
        }
    }

    private fun handleSuccess(data: IndustryResponse?) {
        when {
            data == null -> _filterState.value = IndustryFilterState.NotFound
            else -> {
                allIndustries = data.items
                _filterState.value = IndustryFilterState.Content(allIndustries)
            }
        }
    }

    private fun handleError(errorCode: Int?) {
        _filterState.value = when (errorCode) {
            NetworkResponseStatus.NOT_FOUND -> IndustryFilterState.NotFound
            NetworkResponseStatus.NO_INTERNET -> IndustryFilterState.NoInternet
            NetworkResponseStatus.SERVER_ERROR -> IndustryFilterState.ServerError
            else -> IndustryFilterState.ServerError // или другая ошибка по умолчанию
        }
    }

    fun onSearchTextDebounce(text: String) {
        _query.value = text
    }

    fun clearSearchQuery() {
        _query.value = ""
    }

    companion object {
        const val DEBOUNCE_DELAY = 300L
    }
}
