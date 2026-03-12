package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.models.Workplace

class ChangeFilterInteractor(
    private val cachingRepository: FilterCachingRepository
) {
    fun cacheIndustry(industry: Industry?) {
        cachingRepository.cacheIndustry(industry)
    }

    fun resetIndustry() {
        cachingRepository.cacheIndustry(null)
    }

    fun resetWorkplace() {
        cachingRepository.cacheWorkplace(null)
    }

    val industry: StateFlow<Industry?> = cachingRepository.industry

    val workplace: StateFlow<Workplace?> = cachingRepository.workplace
}
