package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.Filters

interface FilterRepository {
    fun getFilters(): Filters
    fun setFilters(filters: Filters)
    fun resetFilters()
}
