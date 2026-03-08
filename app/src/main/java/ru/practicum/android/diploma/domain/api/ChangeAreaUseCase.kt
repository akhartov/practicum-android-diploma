package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.Area

class ChangeAreaUseCase(
    private val workplaceCachingRepository: WorkplaceCachingRepository
) {
    fun cacheArea(country: Area?, area: Area?) {
        workplaceCachingRepository.cacheArea(country, area)
    }
}
