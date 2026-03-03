package ru.practicum.android.diploma.domain.models

data class Vacancy(
    val id: String,
    val name: String, // Название вакансии ("Андроид-разработчик")
    val description: String? = null, // Html-разметка или обычный текст
    val salaryString: String? = null, // "от ${SalaryDto.from} до ${SalaryDto.to} ${SalaryDto.currency}"
    val experience: String? = null, // Например, "Нет опыта"
    val schedule: String? = null, // Например, "Полный день"
    val employment: String? = null, // Например, "Полная занятость"
    val employerName: String? = null, // Работодатель название ("Еда")
    val employerLogoUrl: String? = null, // Работодатель логотип
    val employerCity: String? = null, // Работодатель город ("Москва")
    val address: String? = null, // Если адреса нет, то должно отображаться название региона
    val skills: List<String>? = null, // Ключевые навыки, список полученый из VacancyDetailDto.skills
    val contacts: Contacts? = null, // Если в информации о вакансии нет данных о контактах для связи, то раздел «Контакты» не должен отображаться
    val url: String? = null, // Ссылка, чтобы поделиться
)
