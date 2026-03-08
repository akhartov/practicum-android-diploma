package ru.practicum.android.diploma.presentation.filter.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.ChangeFilterInteractor
import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.models.Filters
import ru.practicum.android.diploma.presentation.converters.UiFiltersMapper

class FilterSettingsViewModel(
    private val filterInteractor: FilterInteractor,
    private val changeFilterInteractor: ChangeFilterInteractor,
    private val filtersMapper: UiFiltersMapper,
) : ViewModel() {
    private val _filtersUiState = MutableStateFlow(FilterSettingsState())
    val filtersUiState: StateFlow<FilterSettingsState> = _filtersUiState.asStateFlow()

    private var _filtersStateFlow = MutableStateFlow(Filters())

    init {
        // Первое обновление данных фильтра из хранилища
        _filtersStateFlow.value = filterInteractor.getFilters()

        // Поток обновления UI при изменении внутри FilterSettingsViewModel
        viewModelScope.launch {
            _filtersStateFlow.collect {
                _filtersUiState.value = filtersMapper.map(it)
            }
        }

        // Поток обновления UI при применении кешированных значений рабочего места
        viewModelScope.launch {
            changeFilterInteractor.workplace.collect { workplace ->
                workplace?.workplaceName.let { workplaceName ->
                    _filtersUiState.value = _filtersUiState.value.copy(workplace = workplaceName)
                }
            }
        }

        // Поток обновления UI при применении отрасли
        viewModelScope.launch {
            changeFilterInteractor.industry.collect { industry ->
                _filtersUiState.value = _filtersUiState.value.copy(industry = industry?.name)
            }
        }
    }

    fun changeWithSalaryOnly() {
        _filtersStateFlow.value =
            _filtersStateFlow.value.copy(isIncludeSalary = !_filtersStateFlow.value.isIncludeSalary)
    }

    fun changeSalary(salary: String?) {
        _filtersStateFlow.value = _filtersStateFlow.value.copy(salary = salary)
    }

    fun saveFilter() {
        filterInteractor.setFilters(
            _filtersStateFlow.value.copy(
                industryName = changeFilterInteractor.industry.value?.name,
                industryId = changeFilterInteractor.industry.value?.id,
            )
        )
    }

    fun resetFilter() {
        filterInteractor.resetFilters()
        _filtersStateFlow.value = filterInteractor.getFilters()
    }
}
