package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.AreaShort

class ChangeAreaUseCase(
    private val workplaceCachingRepository: WorkplaceCachingRepository,
) {
    val area: StateFlow<AreaShort?> = workplaceCachingRepository.area

    fun cacheArea(country: AreaShort?, area: AreaShort?) {
        workplaceCachingRepository.cacheArea(country, area)
    }
}
