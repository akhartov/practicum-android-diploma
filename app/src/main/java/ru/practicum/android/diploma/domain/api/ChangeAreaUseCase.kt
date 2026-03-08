package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Area

class ChangeAreaUseCase(
    private val workplaceCachingRepository: WorkplaceCachingRepository,
) {
    val area: StateFlow<Area?> = workplaceCachingRepository.area

    fun cacheArea(country: Area?, area: Area?) {
        workplaceCachingRepository.cacheArea(country, area)
    }
}
