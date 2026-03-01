package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.Filters
import ru.practicum.android.diploma.domain.models.VacancyShortResponse
import ru.practicum.android.diploma.util.Resource

interface FilterInteractor {
    fun searchWithFilters(filters: Filters): Flow<Resource<VacancyShortResponse>>
    fun searchWithoutFilters(): Flow<Resource<VacancyShortResponse>>
}
