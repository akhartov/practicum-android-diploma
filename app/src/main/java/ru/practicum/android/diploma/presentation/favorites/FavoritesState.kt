package ru.practicum.android.diploma.presentation.favorites

import ru.practicum.android.diploma.domain.models.VacancyShort

data class FavoritesState(
    val vacancies: List<VacancyShort> = listOf(),
    val fail: Boolean = false
)
