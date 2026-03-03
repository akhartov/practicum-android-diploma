package ru.practicum.android.diploma.domain

import ru.practicum.android.diploma.domain.models.Vacancy

interface LikeInteractor {
    suspend fun isLiked(id: String): Boolean
    suspend fun getLiked(id: String): Vacancy?
    suspend fun like(vacancy: Vacancy)
    suspend fun dislike(id: String)
}
