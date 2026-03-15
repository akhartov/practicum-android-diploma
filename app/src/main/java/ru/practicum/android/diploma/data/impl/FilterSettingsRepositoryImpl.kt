package ru.practicum.android.diploma.data.impl

import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.api.FilterSettingsRepository

class FilterSettingsRepositoryImpl(
    private val filterRepository: FilterRepository
) : FilterSettingsRepository {
    override fun resetIndustry() {
        filterRepository.setIndustry(industryName = null, industryId = null)
    }

    override fun resetWorkplace() {
        filterRepository.setRegion(countryName = null, countryId = null, regionName = null, regionId = null)
    }

    override fun changeWithSalaryOnly() {
        filterRepository.changeWithSalaryOnly()
    }

    override fun setSalary(salary: String) {
        filterRepository.setSalary(salary)
    }
}
