package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Area

interface WorkplaceCachingRepository {
    val area: StateFlow<Area?>
    val country: StateFlow<Area?>

    fun cacheCountry(country: Area?)
    fun cacheArea(country: Area?, area: Area?)
}
