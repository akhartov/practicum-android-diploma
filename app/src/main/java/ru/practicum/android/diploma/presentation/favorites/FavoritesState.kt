package ru.practicum.android.diploma.presentation.favorites

import ru.practicum.android.diploma.domain.models.VacancyShort

sealed interface FavoritesState {
    data object Fail : FavoritesState
    data class Content(val vacancies: List<VacancyShort> = listOf()) : FavoritesState
}
