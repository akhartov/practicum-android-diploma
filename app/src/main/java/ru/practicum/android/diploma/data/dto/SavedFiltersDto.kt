package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.domain.models.Filters

data class SavedFiltersDto(
    val area: String,
    val industry: String,
    val salary: String,
    val isIncludeSalary: String
)

fun SavedFiltersDto.toFilters(): Filters =
    Filters(
        area = if (area.isEmpty()) null else area.toInt(),
        industry = if (industry.isEmpty()) null else industry.toInt(),
        salary = if (salary.isEmpty()) null else salary.toInt(),
        isIncludeSalary = this.isIncludeSalary.toBoolean()
    )
