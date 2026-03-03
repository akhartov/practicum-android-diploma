package ru.practicum.android.diploma.domain

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.SearchParams

interface SearchPageInteractor {
    val searchParams: StateFlow<SearchParams>

    /**
     * Подготовка первого запроса
     * */
    fun search(text: String)

    /**
     * Запрос на поиск следующей страницы (текущая + 1)
     * */
    fun searchNextPage()

    /**
     * Запрос на поиск выполнен успешно.
     * Можно искать следующую страницу
     * */
    fun prepareNextSearch()

    fun isFirstSearch(): Boolean
}
