package ru.practicum.android.diploma.domain.models

data class VacancyShort(
    val id: String,
    val name: String,
    val salary: Salary?,
    val employer: Employer? = null,
    val url: String? = null,
)
