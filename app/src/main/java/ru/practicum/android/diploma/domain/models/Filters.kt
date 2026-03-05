package ru.practicum.android.diploma.domain.models

import ru.practicum.android.diploma.data.dto.SavedFiltersDto

data class Filters(
    val area: Int?,
    val industry: Int?,
    val salary: Int?,
    val isIncludeSalary: Boolean
)

fun Filters.toSavedFilters(): SavedFiltersDto {
    return SavedFiltersDto(
        area = if (this.area == null) "" else this.area.toString(),
        industry = if (this.industry == null) "" else this.industry.toString(),
        salary = if (this.salary == null) "" else this.salary.toString(),
        isIncludeSalary = this.isIncludeSalary.toString()
    )
}
