package ru.practicum.android.diploma.presentation.filter.industry

import ru.practicum.android.diploma.domain.models.Industry

sealed interface IndustryFilterState {
    data object NotFound : IndustryFilterState // Не удалось получить список отраслей
    data object NoInternet : IndustryFilterState // Нет интернета
    data object ServerError : IndustryFilterState // Ошибка сервера
    data class Content(val industries: List<Industry>) : IndustryFilterState
}
