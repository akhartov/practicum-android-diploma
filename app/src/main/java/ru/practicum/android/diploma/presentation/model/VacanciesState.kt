package ru.practicum.android.diploma.presentation.model

sealed interface VacanciesState {
    object Empty : VacanciesState // Поиск еще не был выполнен, на экране человек с биноклем
    object NotFound : VacanciesState // Не удалось получить список вакансий
    object Loading : VacanciesState // Идет поиск вакансий
    object NoInternet : VacanciesState // Нет интернета
    object ServerError : VacanciesState // Ошибка сервера
    class Content(val vacancies: VacancyShortDetails) : VacanciesState // результат поиска
    class NewPageInProgress(val vacancies: VacancyShortDetails) : VacanciesState // запрос следующей страницы
}
