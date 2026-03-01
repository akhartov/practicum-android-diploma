package ru.practicum.android.diploma.data.dto.vacancy

import kotlinx.serialization.SerialName
import ru.practicum.android.diploma.domain.models.Employer

data class EmployerDto(
    val id: String,
    val name: String,
    @SerialName("logo")
    val logoUrl: String? = null,
)

fun EmployerDto.toEmployer(): Employer {
    return Employer(
        name = this.name,
        logo = this.logoUrl,
        id = this.id,
    )
}
