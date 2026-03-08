package ru.practicum.android.diploma.data.converters

import ru.practicum.android.diploma.data.dto.FilterIndustryDto
import ru.practicum.android.diploma.data.dto.IndustryDtoResponse
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.models.IndustryResponse

class IndustryMapper {
    fun map(filterIndustryDto: FilterIndustryDto): Industry {
        return Industry(
            id = filterIndustryDto.id,
            name = filterIndustryDto.name,
        )
    }

    fun mapResponse(industryDtoResponse: IndustryDtoResponse): IndustryResponse {
        return IndustryResponse(
            items = industryDtoResponse.items.map {
                Industry(
                    id = it.id,
                    name = it.name
                )
            }
        )
    }
}
