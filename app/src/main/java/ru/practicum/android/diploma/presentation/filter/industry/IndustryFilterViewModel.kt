package ru.practicum.android.diploma.presentation.filter.industry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.IndustryInteractor
import ru.practicum.android.diploma.domain.models.IndustryResponse
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

class IndustryFilterViewModel(
    private val industryInteractor: IndustryInteractor
) : ViewModel() {
    // private val client: VacancyApiClient,
    // private val mapper: IndustryMapper,

    private val _filterState = MutableStateFlow<IndustryFilterState?>(null)
    val filterState: StateFlow<IndustryFilterState?> = _filterState.asStateFlow()

    private val _query = MutableStateFlow<String>("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _selectedIndustryId = MutableStateFlow<Int?>(null)
    val selectedIndustryId: StateFlow<Int?> = _selectedIndustryId.asStateFlow()

    fun selectIndustry(id: Int?) {
        _selectedIndustryId.value = id
    }

    init {
        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                runCatching {
//                    val industries = client.getFilterIndustries().map { dtoItem ->
//                        mapper.map(dtoItem)
//                    }
//
//                    _filterState.value = IndustryFilterState.Content(industries)
//                }.onFailure { throwable ->
//                    throwable.message?.let { message -> Log.e("IndustryFilterViewModel", message) }
//
//                    _filterState.value = IndustryFilterState.Fail
//                }
//
//            }

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
    }

    private fun handleSuccess(data: IndustryResponse?) {
        when {
            data == null -> _filterState.value = IndustryFilterState.NotFound
            else -> {
                _filterState.value = IndustryFilterState.Content(data.items)
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
}
