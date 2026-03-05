package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.domain.models.Filters

data class SavedFiltersDto(
    val areaId: Int?,
    val industryId: Int?,
    val salary: Int?,
    val isIncludeSalary: Boolean
)

fun SavedFiltersDto.toFilters(): Filters =
    Filters(
        areaId = areaId,
        industryId = industryId,
        salary = salary,
        isIncludeSalary = isIncludeSalary
    )
