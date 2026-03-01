package ru.practicum.android.diploma.data.dto.vacancy

import ru.practicum.android.diploma.domain.models.Experience

data class ExperienceDto(
    val id: String,
    val name: String,
)

fun ExperienceDto.toExperience(): Experience {
    return Experience(id = this.id, name = this.name)
}
