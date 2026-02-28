package ru.practicum.android.diploma.domain.models

data class Vacancy(
    val id: String,
    val name: String,
    val description: String? = null,
    val salary: Salary?,
    val experience: Experience?,
    val schedule: Schedule?,
    val employment: Employment?,
    val employer: Employer? = null,
    val area: Area? = null,
    val skills: List<String>? = null,
    val url: String? = null,
)
