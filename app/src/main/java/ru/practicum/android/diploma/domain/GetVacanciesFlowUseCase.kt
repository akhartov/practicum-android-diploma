package ru.practicum.android.diploma.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.repository.FavoritesRepository
import ru.practicum.android.diploma.domain.models.VacancyShort

class GetVacanciesFlowUseCase(
    private val favoritesRepository: FavoritesRepository,
) {
    fun getVacanciesFlow(): Flow<List<VacancyShort>> = favoritesRepository.getVacanciesFlow()
}
