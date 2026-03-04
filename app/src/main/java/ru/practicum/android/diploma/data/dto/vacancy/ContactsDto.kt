package ru.practicum.android.diploma.data.dto.vacancy

import ru.practicum.android.diploma.domain.models.Contacts

data class ContactsDto(
    val id: String,
    val name: String,
    val email: String? = null,
    val phones: List<PhonesDto>? = null,
)

fun ContactsDto.toContacts(): Contacts {
    return Contacts(
        name = name,
        email = email ?: "",
        phone = phones?.map { it.toPhone() } ?: emptyList()
    )
}

