package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.AreaShort

interface WorkplaceCachingRepository {
    val area: StateFlow<AreaShort?>
    val country: StateFlow<AreaShort?>

    fun cacheCountry(country: AreaShort?)
    fun cacheArea(country: AreaShort?, area: AreaShort?)
}
