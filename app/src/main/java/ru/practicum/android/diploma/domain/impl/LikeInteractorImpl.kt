package ru.practicum.android.diploma.domain.impl

import ru.practicum.android.diploma.data.repository.FavoritesRepository
import ru.practicum.android.diploma.domain.LikeInteractor
import ru.practicum.android.diploma.domain.models.Vacancy

class LikeInteractorImpl(private val favoritesRepository: FavoritesRepository) : LikeInteractor {
    override suspend fun isLiked(id: String): Boolean {
        return favoritesRepository.isVacancyExists(id)
    }

    override suspend fun getLiked(id: String): Vacancy? {
        return favoritesRepository.getVacancyById(id)
    }

    override suspend fun like(vacancy: Vacancy) {
        favoritesRepository.addVacancy(vacancy)
    }

    override suspend fun dislike(id: String) {
        favoritesRepository.deleteVacancyById(id)
    }
}
