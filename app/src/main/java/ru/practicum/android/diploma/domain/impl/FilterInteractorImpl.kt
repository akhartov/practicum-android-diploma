package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.models.Filters
import ru.practicum.android.diploma.domain.models.VacancyShortResponse
import ru.practicum.android.diploma.util.Resource

class FilterInteractorImpl : FilterInteractor {
    override fun searchWithFilters(filters: Filters): Flow<Resource<VacancyShortResponse>> {
        TODO("Not yet implemented")
    }

    override fun searchWithoutFilters(): Flow<Resource<VacancyShortResponse>> {
        TODO("Not yet implemented")
    }
}
