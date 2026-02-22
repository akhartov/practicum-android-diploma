package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.dto.vacancy.VacancyDetailDto

data class VacancyResponse(
    val found: Int,
    val pages: Int,
    val page: Int,
    val items: List<VacancyDetailDto>
)
