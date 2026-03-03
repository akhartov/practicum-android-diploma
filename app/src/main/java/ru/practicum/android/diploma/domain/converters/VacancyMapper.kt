package ru.practicum.android.diploma.domain.converters

import ru.practicum.android.diploma.data.db.entity.VacancyEntity
import ru.practicum.android.diploma.data.dto.VacancyResponse
import ru.practicum.android.diploma.data.dto.vacancy.SalaryDto
import ru.practicum.android.diploma.data.dto.vacancy.VacancyDto
import ru.practicum.android.diploma.data.dto.vacancy.toContacts
import ru.practicum.android.diploma.domain.models.Contacts
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyShort
import ru.practicum.android.diploma.domain.models.VacancyShortResponse
import ru.practicum.android.diploma.util.SalaryMapper

class VacancyMapper(
    private val salaryMapper: SalaryMapper
) {
    fun mapToEntity(vacancyDto: VacancyDto, insertTime: Long): VacancyEntity {
        return VacancyEntity(
            vacancyId = vacancyDto.id,
            insertTime = insertTime,
            vacancyName = vacancyDto.name,
            employerCity = vacancyDto.address?.city,
            address = (if (vacancyDto.address != null) {
                vacancyDto.address.raw
            } else {
                vacancyDto.area
            }) as? String,
            employerName = vacancyDto.employer?.name,
            employerLogoUrl = vacancyDto.employer?.logoUrl,
            salaryString = getSalarySting(vacancyDto.salary),
            experience = vacancyDto.experience?.name,
            schedule = vacancyDto.schedule?.name,
            employment = vacancyDto.employment?.name,
            description = vacancyDto.description,
            skills = vacancyDto.skills,
            url = vacancyDto.url
        )
    }

    fun mapToVancancy(vacancyDto: VacancyDto): Vacancy {
        return Vacancy(
            id = vacancyDto.id,
            name = vacancyDto.name,
            salaryString = getSalarySting(vacancyDto.salary),
            experience = vacancyDto.experience?.name,
            schedule = vacancyDto.schedule?.name,
            employment = vacancyDto.employment?.name,
            employerName = vacancyDto.employer?.name,
            employerLogoUrl = vacancyDto.employer?.logoUrl,
            employerCity = vacancyDto.address?.city,
            address = (if (vacancyDto.address != null) {
                vacancyDto.address.raw
            } else {
                vacancyDto.area
            })  as? String,
            description = vacancyDto.description,
            contacts = vacancyDto.contacts?.toContacts(),
            skills = vacancyDto.skills,
            url = vacancyDto.url,
        )
    }

    fun vacancyToEntity(vacancy: Vacancy, insertTime: Long): VacancyEntity {
        return VacancyEntity(
            vacancyId = vacancy.id,
            insertTime = insertTime,
            vacancyName = vacancy.name,
            employerCity = vacancy.employerCity,
            address = vacancy.address,
            employerName = vacancy.employerName,
            employerLogoUrl = vacancy.employerLogoUrl,
            salaryString = vacancy.salaryString,
            experience = vacancy.experience,
            schedule = vacancy.schedule,
            employment = vacancy.employment,
            description = vacancy.description,
            skills = vacancy.skills,
            contactsName = vacancy.contacts?.name,
            contactsEmail = vacancy.contacts?.email,
            contactsPhones = vacancy.contacts?.phone,
            url = vacancy.url
        )
    }

    fun entityToVacancy(vacancyEntity: VacancyEntity): Vacancy {
        return Vacancy(
            id = vacancyEntity.vacancyId,
            name = vacancyEntity.vacancyName,
            salaryString = vacancyEntity.salaryString,
            experience = vacancyEntity.experience,
            schedule = vacancyEntity.schedule,
            employment = vacancyEntity.employment,
            address = vacancyEntity.address,
            employerName = vacancyEntity.employerName,
            employerLogoUrl = vacancyEntity.employerLogoUrl,
            employerCity = vacancyEntity.employerCity,
            description = vacancyEntity.description,
            skills = vacancyEntity.skills,
            contacts = if (vacancyEntity.contactsName != null || vacancyEntity.contactsEmail != null || vacancyEntity.contactsPhones != null) {
                Contacts(
                    name = vacancyEntity.contactsName ?: "",
                    email = vacancyEntity.contactsEmail ?: "",
                    phone = vacancyEntity.contactsPhones ?: emptyList()
                )
            } else {
                null
            }
        )
    }

    fun entityToVacancyShort(vacancyEntity: VacancyEntity): VacancyShort {
        return VacancyShort(
            id = vacancyEntity.vacancyId,
            vacancyTitle = "${vacancyEntity.vacancyName}, ${vacancyEntity.employerCity}",
            employerName = vacancyEntity.employerName,
            employerLogoUrl = vacancyEntity.employerLogoUrl,
            salaryString = vacancyEntity.salaryString,
        )
    }

    fun mapToVacancyShort(vacancyDto: VacancyDto): VacancyShort {
        return VacancyShort(
            id = vacancyDto.id,
            vacancyTitle = getTitle(vacancyDto),
            employerName = vacancyDto.employer?.name,
            employerLogoUrl = vacancyDto.employer?.logoUrl,
            salaryString = getSalarySting(vacancyDto.salary)
        )
    }

    fun mapResponse(vacancyResponse: VacancyResponse): VacancyShortResponse {
        return VacancyShortResponse(
            found = vacancyResponse.found,
            pages = vacancyResponse.pages,
            page = vacancyResponse.page,
            items = vacancyResponse.items.map {
                VacancyShort(
                    id = it.id,
                    vacancyTitle = getTitle(it),
                    employerName = it.employer?.name,
                    employerLogoUrl = it.employer?.logoUrl,
                    salaryString = getSalarySting(it.salary)
                )
            }
        )
    }

    private fun getTitle(vacancyDto: VacancyDto): String {
        return vacancyDto.address?.city?.let { "${vacancyDto.name}, ${vacancyDto.address.city}" }
            ?: vacancyDto.name
    }

    private fun getSalarySting(salaryDto: SalaryDto?): String {
        return salaryMapper.getSalaryInfo(
            salaryDto?.from,
            salaryDto?.to,
            salaryDto?.currency ?: ""
        )
    }
}
