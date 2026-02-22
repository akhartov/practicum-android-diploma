package ru.practicum.android.diploma.util

import java.util.Locale.getDefault

class CurrencyMapper {

    fun codeToSymbol(currencyCode: String): String {
        return when (currencyCode.uppercase(getDefault())) {
            "USD" -> "$"
            "EUR" -> "€"
            "RUB" -> "₽"
            "GBP" -> "£"
            "JPY" -> "¥"
            "CNY" -> "¥"
            "AUD" -> "$"
            "CAD" -> "$"
            "CHF" -> "Fr"
            "SEK" -> "kr"
            "NOK" -> "kr"
            "DKK" -> "kr"
            "PLN" -> "zł"
            "CZK" -> "Kč"
            "HUF" -> "Ft"
            "TRY" -> "₺"
            "ZAR" -> "R"
            "BRL" -> "$"
            "INR" -> "₹"
            "KRW" -> "₩"
            else -> currencyCode
        }
    }
}
