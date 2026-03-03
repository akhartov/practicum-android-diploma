package ru.practicum.android.diploma.domain.models

data class SearchParams(
    val text: String,
    val page: Int = 0
)
