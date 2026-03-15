package ru.practicum.android.diploma.data.dto

data class SavedFiltersDto(
    val countryName: String? = null,
    val countryId: Int? = null,
    val regionName: String? = null,
    val regionId: Int? = null,
    val industryName: String? = null,
    val industryId: Int? = null,
    val salary: String = "",
    val isIncludeSalary: Boolean = false,
)
