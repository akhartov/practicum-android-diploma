package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.Response
import ru.practicum.android.diploma.data.dto.vacancy.VacancyDetailDto
import ru.practicum.android.diploma.data.dto.vacancy.toVacancyShort
import ru.practicum.android.diploma.domain.models.VacancyShortResponse

data class VacancyResponse(
    val found: Int,
    val pages: Int,
    val page: Int,
    val items: List<VacancyDetailDto>
) : Response()

fun VacancyResponse.toVacancyShortResponse(): VacancyShortResponse {
    return VacancyShortResponse(
        found = this.found,
        pages = this.pages,
        page = this.page,
        items = this.items.map { it.toVacancyShort() }
    )
}
