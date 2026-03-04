package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyShort

interface FavoritesRepository {
    suspend fun isVacancyExists(id: String): Boolean

    suspend fun getVacancyById(id: String): Vacancy?

    suspend fun getVacanciesRange(offset: Int, count: Int): List<VacancyShort>

    suspend fun addVacancy(vacancy: Vacancy)

    suspend fun deleteVacancyById(id: String)

    fun getVacanciesFlow(): Flow<List<VacancyShort>>
}
