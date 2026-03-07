package ru.practicum.android.diploma.presentation.filter.industry

import ru.practicum.android.diploma.domain.models.Industry

sealed interface IndustryFilterState {
    data object Fail : IndustryFilterState
    data class Content(val industries: List<Industry>) : IndustryFilterState
}
