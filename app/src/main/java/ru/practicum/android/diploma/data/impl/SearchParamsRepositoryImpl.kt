package ru.practicum.android.diploma.data.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.data.converters.FiltersMapper
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.api.SearchParamsRepository

class SearchParamsRepositoryImpl(
    private val filterRepository: FilterRepository,
    private val filtersMapper: FiltersMapper,
) : SearchParamsRepository {
    var lastText = ""
    val searchParamsEmitter = MutableStateFlow<HashMap<String, String>>(hashMapOf())
    override val searchParamsFlow: StateFlow<Map<String, String>> = searchParamsEmitter

    override suspend fun emitSearch(text: String, pageIndex: Int) {
        lastText = text
        val hashMap = filtersMapper.filtersToHashMap(filterRepository.getFilters())
        hashMap.put(FIELD_TEXT, lastText)
        hashMap.put(FIELD_PAGE, pageIndex.toString())
        searchParamsEmitter.emit(hashMap)
    }

    override suspend fun emitSearch() {
        val hashMap = filtersMapper.filtersToHashMap(filterRepository.getFilters())
        hashMap.put(FIELD_TEXT, lastText)
        hashMap.put(FIELD_PAGE, FIRST_PAGE_INDEX.toString())
        searchParamsEmitter.emit(hashMap)
    }

    companion object {
        const val FIRST_PAGE_INDEX = 1
        const val FIELD_TEXT = "text"
        const val FIELD_PAGE = "page"
    }
}
