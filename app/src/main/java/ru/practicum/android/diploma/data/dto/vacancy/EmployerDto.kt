package ru.practicum.android.diploma.data.dto.vacancy

import kotlinx.serialization.SerialName

data class EmployerDto(
    val id: Int,
    val name: String,
    @SerialName("logo")
    val logoUrl: String? = null,
)
