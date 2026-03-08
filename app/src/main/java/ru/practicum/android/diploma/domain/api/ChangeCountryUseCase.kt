package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Area

class ChangeCountryUseCase(
    private val workplaceCachingRepository: WorkplaceCachingRepository
) {
    val country: StateFlow<Area?> = workplaceCachingRepository.country

    fun cacheCountry(country: Area?) {
        workplaceCachingRepository.cacheCountry(country)
    }
}
