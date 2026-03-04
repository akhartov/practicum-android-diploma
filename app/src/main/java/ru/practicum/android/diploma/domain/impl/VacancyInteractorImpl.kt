package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.VacancyInteractor
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.util.Resource

class VacancyInteractorImpl(private val vacancyRepository: VacancyRepository) : VacancyInteractor {
    override fun getVacancyById(id: String): Flow<Resource<Vacancy>> {
        return vacancyRepository.getVacancy(id)
    }
}
