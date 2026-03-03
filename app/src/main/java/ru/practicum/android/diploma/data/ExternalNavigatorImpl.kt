package ru.practicum.android.diploma.data

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri
import ru.practicum.android.diploma.domain.api.ExternalNavigator
import ru.practicum.android.diploma.domain.models.MailData

class ExternalNavigatorImpl(private val context: Context) : ExternalNavigator {
    override fun shareLink(link: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            link
        )

        try {
            context.startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            ex.printStackTrace()
        } catch (ex: RuntimeException) {
            ex.printStackTrace()
        }
    }

    override fun call(phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_DIAL)
            .apply {
                data = "tel:$phoneNumber".toUri()
            }

        try {
            context.startActivity(callIntent)
        } catch (ex: ActivityNotFoundException) {
            ex.printStackTrace()
        } catch (ex: RuntimeException) {
            ex.printStackTrace()
        }
    }

    override fun openEmail(data: MailData) {
        val mailIntent = Intent(Intent.ACTION_SENDTO).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        mailIntent.data = Uri.parse("mailto:")
        mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(data.email))
        mailIntent.putExtra(
            Intent.EXTRA_SUBJECT, data.topic
        )
        context.startActivity(mailIntent)
    }

}
