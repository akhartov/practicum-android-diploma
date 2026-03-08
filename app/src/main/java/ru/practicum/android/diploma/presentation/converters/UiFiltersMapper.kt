package ru.practicum.android.diploma.presentation.converters

import ru.practicum.android.diploma.domain.models.Filters
import ru.practicum.android.diploma.presentation.filter.settings.FilterSettingsState

class UiFiltersMapper {
    fun map(filters: Filters): FilterSettingsState {
        return FilterSettingsState(
            workplace = filters.workplaceName,
            industry = filters.industryName,
            salary = filters.salary,
            isIncludeSalary = filters.isIncludeSalary,
        )
    }
}
