package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.Filters

interface FilterInteractor {
    fun getFilters(): Filters
    fun setFilters(filters: Filters)
    fun resetFilters()
    fun prepareQueryParams(): HashMap<String, String>
    fun getFilterIconState(): Boolean
}
