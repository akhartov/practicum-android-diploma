package ru.practicum.android.diploma.data.converters

import ru.practicum.android.diploma.data.dto.SavedFiltersDto
import ru.practicum.android.diploma.domain.models.Filters

class FiltersMapper {
    fun mapToSavedFiltersDto(filters: Filters): SavedFiltersDto =
        SavedFiltersDto(
            area = filters.workplaceName ?: "",
            areaId = filters.areaId?.toString() ?: "",
            industry = filters.industryName ?: "",
            industryId = filters.industryId?.toString() ?: "",
            salary = filters.salary?.toString() ?: "",
            isIncludeSalary = filters.isIncludeSalary.toString()
        )

    fun mapToFilters(savedFiltersDto: SavedFiltersDto): Filters =
        Filters(
            workplaceName = savedFiltersDto.area.ifEmpty { null },
            areaId = if (savedFiltersDto.areaId.isEmpty()) null else savedFiltersDto.areaId.toInt(),
            industryName = savedFiltersDto.industry.ifEmpty { null },
            industryId = if (savedFiltersDto.industryId.isEmpty()) null else savedFiltersDto.industryId.toInt(),
            salary = if (savedFiltersDto.salary.isEmpty()) null else savedFiltersDto.salary.toInt(),
            isIncludeSalary = savedFiltersDto.isIncludeSalary.toBoolean()
        )
}
