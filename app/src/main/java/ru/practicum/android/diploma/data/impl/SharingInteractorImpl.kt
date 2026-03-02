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
        if (trimmedText.startsWith(HTTP_SCHEMA) || trimmedText.startsWith(HTTPS_SCHEMA)) {
            return trimmedText
        }

        return if (trimmedText.isNotEmpty()) {
            "$HTTPS_SCHEMA$trimmedText"
        } else {
            ""
        }
    }

    companion object {
        const val HTTP_SCHEMA = "http://"
        const val HTTPS_SCHEMA = "https://"
    }
}
