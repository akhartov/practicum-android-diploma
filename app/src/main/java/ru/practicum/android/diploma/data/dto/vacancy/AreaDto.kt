package ru.practicum.android.diploma.data.dto.vacancy

data class AreaDto(
    val id: Int,
    val parentId: Int,
    val name: String,
    val areas: List<AreaDto>? = null,
)
