package ru.practicum.android.diploma.domain.models

data class Filters(
    val area: String? = null,
    val areaId: Int? = null,
    val industry: String? = null,
    val industryId: Int? = null,
    val salary: Int? = null,
    val isIncludeSalary: Boolean = false
)
