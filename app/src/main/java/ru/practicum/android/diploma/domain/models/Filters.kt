package ru.practicum.android.diploma.domain.models

data class Filters(
    val workplaceName: String? = null,
    val areaId: Int? = null,
    val industryName: String? = null,
    val industryId: Int? = null,
    val salary: Int? = null,
    val isIncludeSalary: Boolean = false
)
