package ru.practicum.android.diploma.data.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.data.StorageClient
import ru.practicum.android.diploma.data.converters.FiltersMapper
import ru.practicum.android.diploma.data.dto.SavedFiltersDto
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.models.Filters

class FilterRepositoryImpl(
    private val storage: StorageClient<SavedFiltersDto>,
    private val filtersMapper: FiltersMapper,
) : FilterRepository {
    private val _filtersState = MutableStateFlow<Filters>(getFilters())
    override val filtersFlow: StateFlow<Filters> = _filtersState.asStateFlow()

    override fun getFilters(): Filters {
        return filtersMapper.mapToFilters(storage.getData() ?: SavedFiltersDto())
    }

    override fun setFilters(filters: Filters) {
        storage.storeData(filtersMapper.mapToSavedFiltersDto(filters))
        _filtersState.value = filters
    }

    override fun resetFilters() {
        storage.storeData(SavedFiltersDto())
        _filtersState.value = Filters()
    }

    override fun setCountry(countryName: String?, countryId: Int?) {
        val data = storage.getData() ?: SavedFiltersDto()
        storage.storeData(
            data.copy(
                countryName = countryName,
                countryId = countryId,
            )
        )
        _filtersState.value = getFilters()
    }

    override fun setRegion(countryName: String?, countryId: Int?, regionName: String?, regionId: Int?) {
        val data = storage.getData() ?: SavedFiltersDto()
        storage.storeData(
            data.copy(
                countryName = countryName,
                countryId = countryId,
                regionName = regionName,
                regionId = regionId,
            )
        )
        _filtersState.value = getFilters()
    }

    override fun setIndustry(industryName: String?, industryId: Int?) {
        val data = storage.getData() ?: SavedFiltersDto()
        storage.storeData(
            data.copy(
                industryName = industryName,
                industryId = industryId,
            )
        )
        _filtersState.value = getFilters()
    }

    override fun changeWithSalaryOnly() {
        val data = storage.getData() ?: SavedFiltersDto()
        storage.storeData(
            data.copy(
                isIncludeSalary = !data.isIncludeSalary,
            )
        )
        _filtersState.value = getFilters()
    }

    override fun setSalary(salary: String) {
        val data = storage.getData() ?: SavedFiltersDto()
        storage.storeData(
            data.copy(
                salary = salary,
            )
        )
        _filtersState.value = getFilters()
    }
}
