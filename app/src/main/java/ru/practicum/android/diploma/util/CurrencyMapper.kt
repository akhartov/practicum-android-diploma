package ru.practicum.android.diploma.util

import java.util.Locale.getDefault

class CurrencyMapper {

    fun codeToSymbol(currencyCode: String): String {
        return when (currencyCode.uppercase(getDefault())) {
            "RUB" -> "₽"
            "RUR" -> "₽"
            "BYR" -> "Br"
            "USD" -> "$"
            "EUR" -> "€"
            "KZT" -> "₸"
            "UAH" -> "₴"
            "AZN" -> "₼"
            "UZS" -> "So'm"
            "GEL" -> "₾"
            "KGT" -> "₸"
            else -> currencyCode
        }
    }
}
