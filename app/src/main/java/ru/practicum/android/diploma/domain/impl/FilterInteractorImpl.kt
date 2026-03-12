package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.api.SearchParamsRepository
import ru.practicum.android.diploma.domain.models.Filters

class FilterInteractorImpl(
    private val filterRepository: FilterRepository,
    private val searchParamsRepository: SearchParamsRepository,
) : FilterInteractor {

    override val searchParamsFlow: StateFlow<Map<String, String>> = searchParamsRepository.searchParamsFlow

    override suspend fun emitSearch(text: String, pageIndex: Int) {
        searchParamsRepository.emitSearch(text, pageIndex)
    }

    override suspend fun emitSearch() {
        searchParamsRepository.emitSearch()
    }

    override fun getFilters(): Filters {
        return filterRepository.getFilters()
    }

    override fun setFilters(filters: Filters) {
        filterRepository.setFilters(filters)
    }

    override fun resetFilters() {
        filterRepository.resetFilters()
    }

    override fun getFilterIconState(): Boolean {
        val filters = getFilters()
        return filters.areaId != null
            || filters.industryId != null
            || !filters.salary.isNullOrBlank()
            || filters.isIncludeSalary
    }
}
