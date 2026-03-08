package ru.practicum.android.diploma.presentation.filter.settings

data class FilterSettingsState(
    val workplace: String? = null,
    val industry: String? = null,
    val salary: String? = null,
    val isIncludeSalary: Boolean = false
)
