package ru.practicum.android.diploma.presentation.filter.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.ChangeFilterSettingsInteractor
import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.api.FiltersWatchingUseCase
import ru.practicum.android.diploma.presentation.converters.UiFiltersMapper

class FilterSettingsViewModel(
    private val filterInteractor: FilterInteractor,
    private val changeFilterSettingsInteractor: ChangeFilterSettingsInteractor,
    private val filtersWatchingUseCase: FiltersWatchingUseCase,
    private val filtersMapper: UiFiltersMapper,
) : ViewModel() {
    private val _filtersUiState = MutableStateFlow(FilterSettingsState())
    val filtersUiState: StateFlow<FilterSettingsState> = _filtersUiState.asStateFlow()

    init {
        // Поток обновления UI при сохранении фильтра
        viewModelScope.launch {
            filtersWatchingUseCase.filtersFlow.collect {
                _filtersUiState.value = filtersMapper.map(it)
            }
        }
    }

    fun resetWorkplace() {
        changeFilterSettingsInteractor.resetWorkplace()
    }

    fun resetIndustry() {
        changeFilterSettingsInteractor.resetIndustry()
    }

    fun changeWithSalaryOnly() {
        changeFilterSettingsInteractor.changeWithSalaryOnly()
    }

    fun setSalary(salary: String) {
        changeFilterSettingsInteractor.setSalary(salary)
    }

    fun leaveFilterSettings() {
        // ничего не нужно делать при выходе из окна
    }

    fun updateSearch() {
        viewModelScope.launch {
            filterInteractor.emitSearch()
        }
    }

    fun resetFilters() {
        filterInteractor.resetFilters()
    }
}
