package ru.practicum.android.diploma.presentation.filter.country

import ru.practicum.android.diploma.domain.models.AreaShort

sealed interface CountryFilterState {
    object Loading : CountryFilterState
    object NoInternet : CountryFilterState
    object NotFound : CountryFilterState
    object ServerError : CountryFilterState
    data class Content(val countries: List<AreaShort>) : CountryFilterState
}
