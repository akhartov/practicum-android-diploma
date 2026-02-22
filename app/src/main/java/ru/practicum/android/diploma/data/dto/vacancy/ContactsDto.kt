package ru.practicum.android.diploma.data.dto.vacancy

data class ContactsDto(
    val name: String,
    val email: String? = null,
    val phones: List<PhonesDto>? = null,
)
