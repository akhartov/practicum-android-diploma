package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.AreaShort

class ChangeCountryUseCase(
    private val workplaceCachingRepository: WorkplaceCachingRepository
) {
    val country: StateFlow<AreaShort?> = workplaceCachingRepository.country

    fun cacheCountry(country: AreaShort?) {
        workplaceCachingRepository.cacheCountry(country)
    }
}
