package ru.practicum.android.diploma.domain.models

data class VacancyShort(
    val id: String,
    val vacancyTitle: String?, // "${VacancyDetailDto.name}, {AddressDto.city}"
    val employerName: String? = null, // Работодатель название EmployerDto.name
    val employerLogoUrl: String? = null, // Работодатель логотип EmployerDto.logoUrl
    val salaryString: String? = null, // "от ${SalaryDto.from} до ${SalaryDto.to} ${SalaryDto.currency}"
)
