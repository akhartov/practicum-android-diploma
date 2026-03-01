package ru.practicum.android.diploma.data.dto.vacancy

data class AddressDto(
    val id: Int,
    val city: String? = null,
    val street: String? = null,
    val building: String? = null,
    val raw: String? = null,
)
