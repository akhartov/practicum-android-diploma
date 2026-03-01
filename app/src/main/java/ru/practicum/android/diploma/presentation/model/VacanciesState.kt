package ru.practicum.android.diploma.presentation.model

import ru.practicum.android.diploma.domain.models.VacancyShort
import ru.practicum.android.diploma.domain.models.VacancyShortResponse

sealed interface VacanciesState {
    object Empty : VacanciesState // Поиск еще не был выполнен, на экране человек с биноклем
    object NotFound : VacanciesState // Не удалось получить список вакансий
    object Loading : VacanciesState // Идет поиск вакансий
    object NoInternet : VacanciesState // Нет интернета
    object ServerError : VacanciesState // Ошибка сервера
    class Content(val vacancies: VacancyShortResponse) : VacanciesState // результат поиска
    class NewPageInProgress(val vacancies: VacancyShort) : VacanciesState // запрос следующей страницы
}
