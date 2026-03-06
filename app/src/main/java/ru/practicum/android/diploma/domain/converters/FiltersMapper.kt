package ru.practicum.android.diploma.domain.converters

import ru.practicum.android.diploma.data.dto.SavedFiltersDto
import ru.practicum.android.diploma.domain.models.Filters

class FiltersMapper {
    fun mapToSavedFiltersDto(filters: Filters): SavedFiltersDto =
        SavedFiltersDto(
            areaId = filters.areaId,
            industryId = filters.industryId,
            salary = filters.salary,
            isIncludeSalary = filters.isIncludeSalary
        )
}
