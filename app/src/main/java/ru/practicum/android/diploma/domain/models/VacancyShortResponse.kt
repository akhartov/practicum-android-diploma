package ru.practicum.android.diploma.domain.models

class VacancyShortResponse(
    val found: Int,
    val pages: Int,
    val page: Int,
    val items: List<VacancyShort>
) {
    companion object {
        val Empty = VacancyShortResponse(0, 0, 0, listOf())
    }
}
