package ru.practicum.android.diploma.presentation.model

data class VacancyDetails(
    val vacancyName: String?, // Название вакансии ("Андроид-разработчик")
    val employerName: String? = null, // Работодатель название ("Еда")
    val employerLogoUrl: String? = null, // Работодатель логотип
    val employerCity: String? = null, // Работодатель город ("Москва")
    val salary: String? = null, // "от ${SalaryDto.from} до ${SalaryDto.to} ${SalaryDto.currency}"
    val experience: String? = null, // "${VacancyDetailDto.schedule}, ${VacancyDetailDto.employment}"
    val descriptions: List<VacancyDescription>? = null, // Заголовок и пункты VacancyDetailDto.description
    val skills: List<String>? = null, // Ключевые навыки, список полученый из VacancyDetailDto.skills
)
