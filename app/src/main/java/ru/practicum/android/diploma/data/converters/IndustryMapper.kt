package ru.practicum.android.diploma.data.converters

import ru.practicum.android.diploma.data.dto.FilterIndustryDto
import ru.practicum.android.diploma.domain.models.Industry

class IndustryMapper {
    fun map(filterIndustryDto: FilterIndustryDto): Industry {
        return Industry(
            id = filterIndustryDto.id,
            name = filterIndustryDto.name,
        )
    }
}
