package ru.practicum.android.diploma.presentation.model

sealed interface VacancyDetailsState {
    object Loading : VacancyDetailsState // Запрос вакансии в процессе
    object NotFound : VacancyDetailsState // Вакансия не найдена или удалена
    object ServerError : VacancyDetailsState // Ошибка сервера
    class Content(val details: VacancyDetails) : VacancyDetailsState
}
