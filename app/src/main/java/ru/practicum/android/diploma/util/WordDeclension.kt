package ru.practicum.android.diploma.util

import android.content.Context
import ru.practicum.android.diploma.R

class WordDeclension(val context: Context) {
    fun getFoundVacanciesString(count: Int): String {
        return context.resources.getQuantityString(R.plurals.found_n_vacancies, count, count)
    }

    fun getYears(count: Int): String {
        return context.resources.getQuantityString(R.plurals.year_n, count, count)
    }
}
