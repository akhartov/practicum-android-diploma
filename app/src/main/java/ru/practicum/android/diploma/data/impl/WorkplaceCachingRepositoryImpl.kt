package ru.practicum.android.diploma.data.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.domain.api.WorkplaceCachingRepository
import ru.practicum.android.diploma.domain.models.Area

class WorkplaceCachingRepositoryImpl : WorkplaceCachingRepository {
    private val _area = MutableStateFlow<Area?>(null)
    override val area: StateFlow<Area?> = _area.asStateFlow()

    private val _country = MutableStateFlow<Area?>(null)
    override val country: StateFlow<Area?> = _country.asStateFlow()

    override fun cacheCountry(country: Area?) {
        _country.value = country
        _area.value = null
    }

    override fun cacheArea(country: Area?, area: Area?) {
        _country.value = country
        _area.value = area
    }
}
