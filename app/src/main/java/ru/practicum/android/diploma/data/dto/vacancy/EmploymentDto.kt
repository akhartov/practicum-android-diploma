package ru.practicum.android.diploma.data.dto.vacancy

import ru.practicum.android.diploma.domain.models.Employment

data class EmploymentDto(
    val id: String,
    val name: String,
)

fun EmploymentDto.toEmployment(): Employment {
    return Employment(id = this.id, name = this.name)
}
