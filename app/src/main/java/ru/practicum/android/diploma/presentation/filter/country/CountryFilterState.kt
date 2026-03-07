package ru.practicum.android.diploma.presentation.filter.country

import ru.practicum.android.diploma.domain.models.AreaShort

sealed interface CountryFilterState {
    object Loading : CountryFilterState
    object Fail : CountryFilterState
    data class Content(val countries: List<AreaShort>) : CountryFilterState
}
