package ru.practicum.android.diploma.data.converters

import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.SavedFiltersDto
import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.domain.models.Filters

class FiltersMapper {
    fun mapToSavedFiltersDto(filters: Filters): SavedFiltersDto =
        SavedFiltersDto(
            countryName = filters.countryName,
            countryId = filters.countryId,
            regionName = filters.regionName,
            regionId = filters.regionId,
            industryName = filters.industryName,
            industryId = filters.industryId,
            salary = filters.salary,
            isIncludeSalary = filters.isIncludeSalary
        )

    fun mapToFilters(savedFiltersDto: SavedFiltersDto): Filters =
        Filters(
            countryName = savedFiltersDto.countryName,
            countryId = savedFiltersDto.countryId,
            regionName = savedFiltersDto.regionName,
            regionId = savedFiltersDto.regionId,
            industryName = savedFiltersDto.industryName,
            industryId = savedFiltersDto.industryId,
            salary = savedFiltersDto.salary,
            isIncludeSalary = savedFiltersDto.isIncludeSalary
        )

    fun mapToArea(dto: FilterAreaDto): Area =
        Area(
            id = dto.id,
            name = dto.name,
            parentId = dto.parentId,
            areas = dto.areas?.map { mapToArea(it) } ?: emptyList()
        )

    fun filtersToHashMap(filters: Filters): HashMap<String, String> {
        return hashMapOf<String, String>().apply {
            filters.regionId?.let { put("area", it.toString()) }
            filters.industryId?.let { put("industry", it.toString()) }
            if (filters.salary.isNotBlank()) {
                put("salary", filters.salary)
            }
            if (filters.isIncludeSalary) {
                put("only_with_salary", true.toString())
            }
        }
    }
}
