package ru.practicum.android.diploma.data.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.domain.api.WorkplaceCachingRepository
import ru.practicum.android.diploma.domain.models.AreaShort

class WorkplaceCachingRepositoryImpl : WorkplaceCachingRepository {
    private val _area = MutableStateFlow<AreaShort?>(null)
    override val area: StateFlow<AreaShort?> = _area.asStateFlow()

    private val _country = MutableStateFlow<AreaShort?>(null)
    override val country: StateFlow<AreaShort?> = _country.asStateFlow()

    override fun cacheCountry(country: AreaShort?) {
        _country.value = country
        _area.value = null
    }

    override fun cacheArea(country: AreaShort?, area: AreaShort?) {
        _country.value = country
        _area.value = area
    }

    override fun reset() {
        _country.value = null
        _area.value = null
    }
}
