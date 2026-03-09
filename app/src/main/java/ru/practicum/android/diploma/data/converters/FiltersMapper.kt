package ru.practicum.android.diploma.data.converters

import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.SavedFiltersDto
import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.domain.models.Filters

class FiltersMapper {
    fun mapToSavedFiltersDto(filters: Filters): SavedFiltersDto =
        SavedFiltersDto(
            area = filters.workplaceName ?: "",
            areaId = filters.areaId?.toString() ?: "",
            industry = filters.industryName ?: "",
            industryId = filters.industryId?.toString() ?: "",
            salary = filters.salary ?: "",
            isIncludeSalary = filters.isIncludeSalary.toString()
        )

    fun mapToFilters(savedFiltersDto: SavedFiltersDto): Filters =
        Filters(
            workplaceName = savedFiltersDto.area.ifEmpty { null },
            areaId = if (savedFiltersDto.areaId.isEmpty()) null else savedFiltersDto.areaId.toInt(),
            industryName = savedFiltersDto.industry.ifEmpty { null },
            industryId = if (savedFiltersDto.industryId.isEmpty()) null else savedFiltersDto.industryId.toInt(),
            salary = savedFiltersDto.salary.ifEmpty { null },
            isIncludeSalary = savedFiltersDto.isIncludeSalary.toBoolean()
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
            filters.areaId?.let { put("area", it.toString()) }
            filters.industryId?.let { put("industry", it.toString()) }

            if (!filters.salary.isNullOrBlank()) {
                put("salary", filters.salary)
            }
            if (filters.isIncludeSalary) {
                put("only_with_salary", true.toString())
            }
        }
    }
}
