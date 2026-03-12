package ru.practicum.android.diploma.data.impl

import ru.practicum.android.diploma.data.StorageClient
import ru.practicum.android.diploma.data.converters.FiltersMapper
import ru.practicum.android.diploma.data.dto.SavedFiltersDto
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.models.Filters

class FilterRepositoryImpl(
    private val storage: StorageClient<SavedFiltersDto>,
    private val filtersMapper: FiltersMapper,
) : FilterRepository {

    override fun getFilters(): Filters {
        return filtersMapper.mapToFilters(storage.getData() ?: SavedFiltersDto())
    }

    override fun setFilters(filters: Filters) {
        storage.storeData(filtersMapper.mapToSavedFiltersDto(filters))
    }

    override fun resetFilters() {
        storage.storeData(SavedFiltersDto())
    }
}
