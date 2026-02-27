package ru.practicum.android.diploma.data.dto.vacancy

data class VacancyDetailDto(
    val id: String,
    val name: String,
    val salary: SalaryDto? = null,
    val address: AddressDto? = null,
    val experience: ExperienceDto? = null,
    val schedule: ScheduleDto? = null,
    val employment: EmploymentDto? = null,
    val contacts: ContactsDto? = null,
    val description: String? = null,
    val employer: EmployerDto? = null,
    val area: AreaDto? = null,
    val skills: List<String>? = null,
    val url: String? = null,
    val industry: IndustryDto? = null,
)
