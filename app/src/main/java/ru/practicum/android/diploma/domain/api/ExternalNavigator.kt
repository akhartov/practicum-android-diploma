package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.MailData

interface ExternalNavigator {
    fun shareLink(link: String)
    fun call(phoneNumber: String)
    fun openEmail(data: MailData)
}
