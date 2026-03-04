package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.models.Filters
import ru.practicum.android.diploma.ui.filter.workplace.FilterIconType

class FilterInteractorImpl : FilterInteractor {
    override fun prepareQueryParams(filters: Filters): HashMap<String, String> {
        return HashMap()
    }

    override fun getFilterIconState(): Flow<FilterIconType> {
        return emptyFlow()
    }
}
