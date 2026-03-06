package ru.practicum.android.diploma.data.converters

import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.SavedFiltersDto
import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.domain.models.Filters

class FiltersMapper {
    fun mapToSavedFiltersDto(filters: Filters): SavedFiltersDto =
        SavedFiltersDto(
            area = filters.area?.toString() ?: "",
            areaId = filters.areaId?.toString() ?: "",
            industry = filters.industry?.toString() ?: "",
            industryId = filters.industryId?.toString() ?: "",
            salary = filters.salary?.toString() ?: "",
            isIncludeSalary = filters.isIncludeSalary.toString()
        )

    fun mapToFilters(savedFiltersDto: SavedFiltersDto): Filters =
        Filters(
            area = if (savedFiltersDto.area.isEmpty()) null else savedFiltersDto.area,
            areaId = if (savedFiltersDto.areaId.isEmpty()) null else savedFiltersDto.areaId.toInt(),
            industry = if (savedFiltersDto.industry.isEmpty()) null else savedFiltersDto.industry,
            industryId = if (savedFiltersDto.industryId.isEmpty()) null else savedFiltersDto.industryId.toInt(),
            salary = if (savedFiltersDto.salary.isEmpty()) null else savedFiltersDto.salary.toInt(),
            isIncludeSalary = savedFiltersDto.isIncludeSalary.toBoolean()
        )

    fun mapToArea(dto: FilterAreaDto): Area =
        Area(
            id = dto.id,
            name = dto.name,
            parentId = dto.parentId,
            areas = dto.areas?.map { mapToArea(it) } ?: emptyList()
        )
}
