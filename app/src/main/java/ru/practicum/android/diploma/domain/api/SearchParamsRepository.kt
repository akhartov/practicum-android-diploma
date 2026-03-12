package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow

interface SearchParamsRepository {
    val searchParamsFlow: StateFlow<Map<String, String>>

    suspend fun emitSearch(text: String, pageIndex: Int)
    suspend fun emitSearch()
}
