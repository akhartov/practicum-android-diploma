package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.Filters
import ru.practicum.android.diploma.ui.filter.workplace.FilterIconType

interface FilterInteractor {
    fun searchWithFilters(filters: Filters): HashMap<String, String>
    fun searchWithoutFilters(): HashMap<String, String>
    fun getFilterIconState(): Flow<FilterIconType>
}
