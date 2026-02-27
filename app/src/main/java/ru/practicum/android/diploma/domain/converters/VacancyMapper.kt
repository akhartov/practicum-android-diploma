package ru.practicum.android.diploma.domain.converters

import ru.practicum.android.diploma.data.db.entity.VacancyEntity
import ru.practicum.android.diploma.data.dto.vacancy.VacancyDetailDto
import ru.practicum.android.diploma.presentation.model.VacancyDetails
import ru.practicum.android.diploma.presentation.model.VacancyShortDetails
import ru.practicum.android.diploma.util.SalaryMapper

class VacancyMapper(
    private val descriptionSplitter: DescriptionSplitter,
    private val salaryMapper: SalaryMapper
) {
    fun mapToEntity(vacancyDto: VacancyDetailDto, insertTime: Long): VacancyEntity {
        return VacancyEntity(
            vacancyId = vacancyDto.id,
            insertTime = insertTime,
            vacancyName = vacancyDto.name,
            employerCity = vacancyDto.address?.city,
            employerName = vacancyDto.employer?.name,
            employerLogoUrl = vacancyDto.employer?.logoUrl,
            salaryFrom = vacancyDto.salary?.from,
            salaryTo = vacancyDto.salary?.to,
            salaryCurrencyCode = vacancyDto.salary?.currency,
            experience = vacancyDto.experience?.name,
            schedule = vacancyDto.schedule?.name,
            employment = vacancyDto.employment?.name,
            description = vacancyDto.description,
            skills = vacancyDto.skills,
            url = vacancyDto.url
        )
    }

    fun entityToDetails(vacancyEntity: VacancyEntity): VacancyDetails {
        return VacancyDetails(
            vacancyName = vacancyEntity.vacancyName,
            employerName = vacancyEntity.employerName,
            employerLogoUrl = vacancyEntity.employerLogoUrl,
            employerCity = vacancyEntity.employerCity,
            salary = salaryMapper.getSalaryInfo(
                vacancyEntity.salaryFrom,
                vacancyEntity.salaryTo,
                vacancyEntity.salaryCurrencyCode ?: ""
            ),
            experience = vacancyEntity.experience,
            descriptions = descriptionSplitter.splitText(vacancyEntity.description),
            skills = vacancyEntity.skills
        )
    }

    fun entityToShortDetails(vacancyEntity: VacancyEntity): VacancyShortDetails {
        return VacancyShortDetails(
            vacancyTitle = "${vacancyEntity.vacancyName}, ${vacancyEntity.employerCity}",
            employerName = vacancyEntity.employerName,
            employerLogoUrl = vacancyEntity.employerLogoUrl,
            salaryString = salaryMapper.getSalaryInfo(
                vacancyEntity.salaryFrom,
                vacancyEntity.salaryTo,
                vacancyEntity.salaryCurrencyCode ?: ""
            )
        )
    }
}
