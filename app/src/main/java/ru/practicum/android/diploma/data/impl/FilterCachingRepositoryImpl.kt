package ru.practicum.android.diploma.data.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.domain.api.FilterCachingRepository
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.models.Workplace

class FilterCachingRepositoryImpl : FilterCachingRepository {
    private val _industry = MutableStateFlow<Industry?>(null)
    override val industry: StateFlow<Industry?> = _industry.asStateFlow()

    private val _workplace = MutableStateFlow<Workplace?>(null)
    override val workplace: StateFlow<Workplace?> = _workplace.asStateFlow()

    override fun cacheIndustry(inductry: Industry?) {
        _industry.value = inductry
    }

    override fun cacheWorkplace(newWorkplace: Workplace?) {
        _workplace.value = newWorkplace
    }
}
