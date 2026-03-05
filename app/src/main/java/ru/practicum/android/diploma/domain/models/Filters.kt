package ru.practicum.android.diploma.domain.models

import ru.practicum.android.diploma.data.dto.SavedFiltersDto

data class Filters(
    val areaId: Int?,
    val industryId: Int?,
    val salary: Int?,
    val isIncludeSalary: Boolean
)

fun Filters.toSavedFilters(): SavedFiltersDto =
    SavedFiltersDto(
        areaId = areaId,
        industryId = industryId,
        salary = salary,
        isIncludeSalary = isIncludeSalary
    )
