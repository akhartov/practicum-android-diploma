package ru.practicum.android.diploma.domain.impl

import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.models.Filters

class FilterInteractorImpl(private val filterRepository: FilterRepository) : FilterInteractor {
    override fun getFilters(): Filters {
        return filterRepository.getFilters()
    }

    override fun setFilters(filters: Filters) {
        filterRepository.setFilters(filters)
    }

    override fun resetFilters() {
        filterRepository.resetFilters()
    }

    override fun prepareQueryParams(): HashMap<String, String> {
        val filters = getFilters()

        return hashMapOf<String, String>().apply {
            if (filters.areaId != null) {
                put("area", filters.areaId.toString())
            }
            if (filters.industryId != null) {
                put("industry", filters.industryId.toString())
            }
            if (!filters.salary.isNullOrBlank()) {
                put("salary", filters.salary)
            }
            if (filters.isIncludeSalary) {
                put("only_with_salary", true.toString())
            }
        }
    }

    override fun getFilterIconState(): Boolean {
        val filters = getFilters()
        return filters.areaId != null
            || filters.industryId != null
            || !filters.salary.isNullOrBlank()
            || filters.isIncludeSalary
    }
}
