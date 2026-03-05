package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.domain.models.Filters

data class SavedFiltersDto(
    val area: String,
    val industry: String,
    val salary: String,
    val isIncludeSalary: String
)

fun SavedFiltersDto.toFilters(): Filters {
    return Filters(
        area = if (this.area == "") null else this.area.toInt(),
        industry = if (this.industry == "") null else this.industry.toInt(),
        salary = if (this.salary == "") null else this.salary.toInt(),
        isIncludeSalary = this.isIncludeSalary.toBoolean()
    )
}
