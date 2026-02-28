package ru.practicum.android.diploma.data.dto.vacancy

import ru.practicum.android.diploma.domain.models.Area

data class AreaDto(
    val id: Int,
    val parentId: Int,
    val name: String,
    val areas: List<AreaDto>? = null,
)

fun AreaDto.toArea(): Area {
    return Area(
        id = this.id,
        name = this.name,
        parentId = this.parentId,
        areas = this.areas?.map { it.toArea() } ?: emptyList()
    )
}
