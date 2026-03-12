package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Filters

interface FilterInteractor {
    val searchParamsFlow: StateFlow<Map<String, String>>

    suspend fun emitSearch(text: String, pageIndex: Int)
    suspend fun emitSearch()

    fun getFilters(): Filters
    fun setFilters(filters: Filters)
    fun resetFilters()
    fun getFilterIconState(): Boolean
}
