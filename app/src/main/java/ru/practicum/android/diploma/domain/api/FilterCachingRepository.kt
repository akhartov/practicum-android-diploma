package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.models.Workplace

interface FilterCachingRepository {
    val industry: StateFlow<Industry?>
    val workplace: StateFlow<Workplace?>
    fun cacheIndustry(inductry: Industry?)
    fun cacheWorkplace(newWorkplace: Workplace?)
}
