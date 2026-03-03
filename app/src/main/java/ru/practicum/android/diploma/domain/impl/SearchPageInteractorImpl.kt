package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.practicum.android.diploma.domain.SearchPageInteractor
import ru.practicum.android.diploma.domain.models.SearchParams
import java.util.concurrent.atomic.AtomicInteger

class SearchPageInteractorImpl : SearchPageInteractor {
    private var currentPage = AtomicInteger(FIRST_PAGE_INDEX)
    private val _searchParams = MutableStateFlow(SearchParams(text = "", page = FIRST_PAGE_INDEX))

    override val searchParams: StateFlow<SearchParams> = _searchParams.asStateFlow()

    override fun search(text: String) {
        currentPage.set(FIRST_PAGE_INDEX)
        _searchParams.update { it.copy(text = text, page = currentPage.get()) }
    }

    override fun searchNextPage() {
        _searchParams.update { it.copy(text = it.text, page = currentPage.get() + PAGE_INCREMENT) }
    }

    override fun prepareNextSearch() {
        currentPage.incrementAndGet()
    }

    override fun isFirstSearch(): Boolean {
        return currentPage.get() <= FIRST_PAGE_INDEX
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
        private const val PAGE_INCREMENT = 1
    }
}
