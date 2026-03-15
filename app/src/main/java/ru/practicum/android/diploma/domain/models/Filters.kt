package ru.practicum.android.diploma.domain.models

data class Filters(
    val countryName: String? = null,
    val countryId: Int? = null,
    val regionName: String? = null,
    val regionId: Int? = null,
    val industryName: String? = null,
    val industryId: Int? = null,
    /**
     * "" - значение не задано, фильтр пуст
     * true - значение задано положетельным, фильтр не пуст
     * */
    val salary: String = "",
    /**
     * false - значение задано отрицательным, фильтр пуст
     * true - значение задано положетельным, фильтр не пуст
     * */
    val isIncludeSalary: Boolean = false
)
