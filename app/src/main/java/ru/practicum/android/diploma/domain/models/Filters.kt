package ru.practicum.android.diploma.domain.models

import ru.practicum.android.diploma.data.dto.SavedFiltersDto

data class Filters(
    val area: Int?,
    val industry: Int?,
    val salary: Int?,
    val isIncludeSalary: Boolean
)

fun Filters.toSavedFilters(): SavedFiltersDto =
    SavedFiltersDto(
        area = area?.toString() ?: "",
        industry = industry?.toString() ?: "",
        salary = salary?.toString() ?: "",
        isIncludeSalary = isIncludeSalary.toString()
    )
