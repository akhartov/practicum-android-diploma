package ru.practicum.android.diploma.data.dto

data class SavedFiltersDto(
    val area: String,
    val areaId: String,
    val industry: String,
    val industryId: String,
    val salary: String,
    val isIncludeSalary: String
)
