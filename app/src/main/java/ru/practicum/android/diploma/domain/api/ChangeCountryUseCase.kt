package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.Area

class ChangeCountryUseCase(
    private val workplaceCachingRepository: WorkplaceCachingRepository
) {
    fun cacheCountry(country: Area?) {
        workplaceCachingRepository.cacheCountry(country)
    }
}
