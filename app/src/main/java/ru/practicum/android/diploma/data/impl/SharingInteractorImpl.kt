package ru.practicum.android.diploma.data.impl

import android.content.Context
import android.content.Intent
import ru.practicum.android.diploma.domain.SharingInteractor

class SharingInteractorImpl(
    val context: Context,
) : SharingInteractor {

    override fun shareLink(link: String) {
        Intent(Intent.ACTION_SEND).apply {
            setType("text/plain")
            putExtra(Intent.EXTRA_TEXT, ensureValidUrl(link))
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }.also { context.startActivity(it) }
    }

    private fun ensureValidUrl(text: String): String {
        val trimmedText = text.trim()
        if (trimmedText.startsWith("http://") || trimmedText.startsWith("https://")) {
            return trimmedText
        }

        return if (trimmedText.isNotEmpty()) {
            "https://$trimmedText"
        } else {
            ""
        }
    }

}
