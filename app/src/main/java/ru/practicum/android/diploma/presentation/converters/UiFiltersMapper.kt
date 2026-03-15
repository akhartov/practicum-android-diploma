package ru.practicum.android.diploma.presentation.converters

import ru.practicum.android.diploma.domain.models.Filters
import ru.practicum.android.diploma.presentation.filter.settings.FilterSettingsState

class UiFiltersMapper {
    fun map(filters: Filters): FilterSettingsState {
        return FilterSettingsState(
            workplace = buildWorkplaceName(filters.countryName, filters.regionName),
            industry = filters.industryName,
            salary = filters.salary,
            isIncludeSalary = filters.isIncludeSalary,
        )
    }

    private fun buildWorkplaceName(countryName: String?, regionName: String?): String? {
        return when {
            regionName == null && countryName != null -> countryName
            regionName != null && countryName != null -> "$countryName, $regionName"
            regionName != null && countryName == null -> regionName
            else -> null
        }
    }
}
