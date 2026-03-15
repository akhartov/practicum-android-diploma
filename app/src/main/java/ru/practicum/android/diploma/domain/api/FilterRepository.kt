package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Filters

interface FilterRepository {
    val filtersFlow: StateFlow<Filters>
    fun getFilters(): Filters
    fun setFilters(filters: Filters)
    fun resetFilters()

    fun setCountry(countryName: String?, countryId: Int?)
    fun setRegion(countryName: String?, countryId: Int?, regionName: String?, regionId: Int?)

    fun setIndustry(industryName: String?, industryId: Int?)

    fun changeWithSalaryOnly()

    fun setSalary(salary: String)
}
