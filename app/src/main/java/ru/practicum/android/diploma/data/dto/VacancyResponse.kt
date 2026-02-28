package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.Response
import ru.practicum.android.diploma.data.dto.vacancy.VacancyDto

data class VacancyResponse(
    val found: Int,
    val pages: Int,
    val page: Int,
    val items: List<VacancyDto>
) : Response()
