package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.Response

data class IndustryDtoResponse(
    val items: List<FilterIndustryDto>
) : Response()
