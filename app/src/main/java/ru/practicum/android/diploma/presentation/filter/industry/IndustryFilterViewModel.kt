package ru.practicum.android.diploma.presentation.filter.industry

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class IndustryFilterViewModel : ViewModel() {
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

    /*init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    val industries = client.getFilterIndustries().map { dtoItem ->
                        mapper.map(dtoItem)
                    }

                    _filterState.value = IndustryFilterState.Content(industries)
                }.onFailure { throwable ->
                    throwable.message?.let { message -> Log.e("IndustryFilterViewModel", message) }

                    _filterState.value = IndustryFilterState.Fail
                }

            }
        }
    }*/

    fun onSearchTextDebounce(text: String) {
        _query.value = text
    }

    fun clearSearchQuery() {
        _query.value = ""
    }
}
