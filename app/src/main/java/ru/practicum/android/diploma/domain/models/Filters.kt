package ru.practicum.android.diploma.domain.models

data class Filters(
    val area: Area?,
    val industry: Industry?,
    val preferSalary: String?,
    val isIncludeSalary: Boolean?
)
