package ru.practicum.android.diploma.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.data.converters.VacancyMapper
import ru.practicum.android.diploma.data.db.dao.VacancyDao
import ru.practicum.android.diploma.data.repository.FavoritesRepository
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyShort

class FavoritesRepositoryImpl(
    private val vacancyDao: VacancyDao,
    private val vacancyMapper: VacancyMapper,
) : FavoritesRepository {

    override suspend fun isVacancyExists(id: String): Boolean {
        return vacancyDao.existsById(id)
    }

    override suspend fun getVacancyById(id: String): Vacancy? {
        return vacancyDao.getVacancy(id)?.let { entity ->
            vacancyMapper.entityToVacancy(entity)
        }
    }

    override suspend fun getVacanciesRange(offset: Int, count: Int): List<VacancyShort> {
        return vacancyDao.getVacanciesPage(offset, count)
            .map { entity ->
                vacancyMapper.entityToVacancyShort(entity)
            }
    }

    override suspend fun addVacancy(vacancy: Vacancy) {
        vacancyDao.insertVacancy(
            vacancyMapper.vacancyToEntity(
                vacancy,
                System.currentTimeMillis()
            )
        )
    }

    override suspend fun deleteVacancyById(id: String) {
        vacancyDao.deleteVacancyId(id)
    }

    override fun getVacanciesFlow(): Flow<List<VacancyShort>> =
        vacancyDao.getVacanciesFlow().map { entities ->
            entities.map { entity -> vacancyMapper.entityToVacancyShort(entity) }
        }
}
