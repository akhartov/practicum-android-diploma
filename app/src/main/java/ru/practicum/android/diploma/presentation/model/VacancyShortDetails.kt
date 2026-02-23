package ru.practicum.android.diploma.presentation.model

data class VacancyShortDetails(
    val vacancyTitle: String, // "${vacancy.name}, {employer.city}"
    val employerName: String? = null, // Работодатель название
    val employerLogoUrl: String? = null, // Работодатель логотип
    val salaryString: String? = null, // "от $salaryFrom до $salary $salaryCurrencySign"
)
