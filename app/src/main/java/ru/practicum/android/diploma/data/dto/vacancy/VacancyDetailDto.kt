package ru.practicum.android.diploma.data.dto.vacancy

import ru.practicum.android.diploma.data.Response
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyShort

data class VacancyDetailDto(
    val id: String,
    val name: String,
    val salary: SalaryDto?,
    val address: AddressDto?,
    val experience: ExperienceDto?,
    val schedule: ScheduleDto?,
    val employment: EmploymentDto?,
    val contacts: ContactsDto? = null,
    val description: String? = null,
    val employer: EmployerDto? = null,
    val area: AreaDto? = null,
    val skills: List<String>? = null,
    val url: String? = null,
    val industry: IndustryDto? = null,
): Response()

fun VacancyDetailDto.toVacancyShort(): VacancyShort {
    return VacancyShort(
        id = this.id,
        name = this.name,
        salary = this.salary?.toSalary(),
        employer = this.employer?.toEmployer(),
        url = this.url
    )
}

fun VacancyDetailDto.toVacancy(): Vacancy {
    return Vacancy(
        id = this.id,
        name = this.name,
        description = this.description,
        salary = this.salary?.toSalary(),
        experience = this.experience?.toExperience(),
        schedule = this.schedule?.toSchedule(),
        employment = this.employment?.toEmployment(),
        employer = this.employer?.toEmployer(),
        area = this.area?.toArea(),
        skills = this.skills,
        url = this.url,
    )
}

