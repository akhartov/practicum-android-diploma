package ru.practicum.android.diploma.domain.impl

import ru.practicum.android.diploma.domain.api.ExternalNavigator
import ru.practicum.android.diploma.domain.api.SharingInteractor
import ru.practicum.android.diploma.domain.models.MailData

class SharingInteractorImpl(private val externalNavigator: ExternalNavigator) : SharingInteractor {
    override fun shareVacancy(link: String) {
        externalNavigator.shareLink(link)
    }

    override fun callNumber(number: String) {
        externalNavigator.call(number)
    }

    override fun writeMail(data: MailData) {
        externalNavigator.openEmail(data)
    }
}
