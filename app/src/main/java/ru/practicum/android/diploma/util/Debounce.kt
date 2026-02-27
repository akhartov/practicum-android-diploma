package ru.practicum.android.diploma.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Debounce {

    private var searchJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.IO + Job())
    private var searchRequest: (searchText: String) -> Unit = {}
    private var latestSearchText: String = SEARCH_DEFAULT

    // Передаётся запроса поиска и лямда для выполнения самого поиска
    fun searchDebounce(searchText: String) {
        if (latestSearchText.equals(searchText)) {
            return
        }
        latestSearchText = searchText
        clear()
        searchJob = scope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            searchRequest(searchText)
        }
    }

    // Отмена всех корутин
    fun clear() {
        searchJob?.cancel()
    }

    // Устанавливается функция для debounce
    fun setSearchRequest(searchRequest: (searchText: String) -> Unit) {
        this.searchRequest = searchRequest
    }

    companion object {
        const val SEARCH_DEBOUNCE_DELAY = 2000L
        const val SEARCH_DEFAULT = ""
    }
}
