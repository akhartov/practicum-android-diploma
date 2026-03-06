package ru.practicum.android.diploma.domain.models

data class Filters(
    val area: String?,
    val areaId: Int?,
    val industry: String?,
    val industryId: Int?,
    val salary: Int?,
    val isIncludeSalary: Boolean
)
