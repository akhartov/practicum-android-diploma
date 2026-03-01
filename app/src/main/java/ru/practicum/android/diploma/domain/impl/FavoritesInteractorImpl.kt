package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.repository.FavoritesRepository
import ru.practicum.android.diploma.domain.FavoritesInteractor
import ru.practicum.android.diploma.domain.models.FavoritesChanges
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyShort

class FavoritesInteractorImpl(
    private val favoritesRepository: FavoritesRepository,
) : FavoritesInteractor {

    private var lastIndex = 0
    private var _favoritesChangesFlow =
        MutableStateFlow<FavoritesChanges>(FavoritesChanges.Empty)

    override fun changesFlow(): Flow<FavoritesChanges> = _favoritesChangesFlow

    override suspend fun like(vacancy: Vacancy) {
        withContext(Dispatchers.IO) {
            favoritesRepository.addVacancy(vacancy)
        }
        _favoritesChangesFlow.emit(FavoritesChanges.Increased(vacancy.id))
    }

    override suspend fun dislike(vacancyId: String) {
        withContext(Dispatchers.IO) {
            favoritesRepository.deleteVacancyById(vacancyId)
        }

        if (lastIndex > 0) {
            lastIndex--
        }

        _favoritesChangesFlow.emit(FavoritesChanges.Decreased(vacancyId))
    }

    override suspend fun getNewVacancies(count: Int): List<VacancyShort> {
        return withContext(Dispatchers.IO) {
            val vacancies = favoritesRepository.getVacanciesRange(lastIndex, count)
            lastIndex += vacancies.size

            vacancies
        }
    }
}
