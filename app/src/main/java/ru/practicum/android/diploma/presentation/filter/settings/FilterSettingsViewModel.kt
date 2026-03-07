package ru.practicum.android.diploma.presentation.filter.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.models.Filters
import ru.practicum.android.diploma.presentation.converters.UiFiltersMapper

class FilterSettingsViewModel(
    private val filterInteractor: FilterInteractor,
    private val filtersMapper: UiFiltersMapper,
) : ViewModel() {
    private val _filters = MutableStateFlow(FilterSettingsState())
    val filters: StateFlow<FilterSettingsState> = _filters.asStateFlow()

    private var filtersDataFlow = MutableStateFlow(Filters())

    init {
        filtersDataFlow.value = filterInteractor.getFilters()
        viewModelScope.launch {
            filtersDataFlow.collect {
                _filters.value = filtersMapper.map(it)
            }
        }
    }

    fun changeWithSalaryOnly() {
        filtersDataFlow.value = filtersDataFlow.value.copy(isIncludeSalary = !filtersDataFlow.value.isIncludeSalary)
    }

    fun changeSalary(salary: Int?) {
        filtersDataFlow.value = filtersDataFlow.value.copy(salary = salary)
    }

    fun changeIndustry(industryId: Int?, industryName: String?) {
        filtersDataFlow.value = filtersDataFlow.value.copy(industryId = industryId, industry = industryName)
    }

    fun changeArea(areaId: Int?, areaName: String?) {
        filtersDataFlow.value = filtersDataFlow.value.copy(areaId = areaId, area = areaName)
    }

    fun saveFilter() {
        filterInteractor.setFilters(filtersDataFlow.value)
    }

    fun resetFilter() {
        filterInteractor.setFilters(Filters())
        filtersDataFlow.value = filterInteractor.getFilters()
    }
}
