package ru.practicum.android.diploma.presentation.model

import ru.practicum.android.diploma.domain.models.Vacancy

sealed interface VacancyDetailsState {
    object Loading : VacancyDetailsState // Запрос вакансии в процессе
    object NotFound : VacancyDetailsState // Вакансия не найдена или удалена
    object ServerError : VacancyDetailsState // Ошибка сервера
    object NoInternet : VacancyDetailsState // нет интернета
    class Content(val details: Vacancy) : VacancyDetailsState
}
