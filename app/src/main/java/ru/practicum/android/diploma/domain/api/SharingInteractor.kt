package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.MailData

interface SharingInteractor {
    fun shareVacancy(link: String)
    fun callNumber(number: String)
    fun writeMail(data: MailData)
}
