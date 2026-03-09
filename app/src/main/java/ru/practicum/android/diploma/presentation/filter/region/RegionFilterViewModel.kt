package ru.practicum.android.diploma.presentation.filter.region

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.AreaInteractor
import ru.practicum.android.diploma.domain.models.AreaShort
import ru.practicum.android.diploma.presentation.filter.industry.IndustryFilterViewModel
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

class RegionFilterViewModel(
    private val areaInteractor: AreaInteractor
) : ViewModel() {
    private val _regionFilterState = MutableStateFlow<RegionFilterState>(RegionFilterState.Loading)
    val regionFilterState: StateFlow<RegionFilterState> = _regionFilterState.asStateFlow()

    private val _query = MutableStateFlow<String>("")
    val query: StateFlow<String> = _query.asStateFlow()

    var allRegions = listOf<AreaShort>()

    init {
        viewModelScope.launch {

            areaInteractor.getRegions(METHOD_STUB_COUNTRY, _query.value)
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            handleSuccess(resource.data)
                        }

                        is Resource.Error -> {
                            handleError(resource.error)
                        }
                    }
                }
        }

        viewModelScope.launch {
            query.debounce(IndustryFilterViewModel.Companion.DEBOUNCE_DELAY) // пауза
                .distinctUntilChanged() // ограничиваем если есть новый поиск
                .onEach {
                    _regionFilterState.value = RegionFilterState.Loading

                    if (_regionFilterState.value is RegionFilterState.Content) {
                        _regionFilterState.value = RegionFilterState.Content(
                            getFilteredRegions(query.value)
                        )
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun getFilteredRegions(regionQueryString: String): List<AreaShort> {
        val trimmedQuery = regionQueryString.trim()
        return if (trimmedQuery.isEmpty()) {
            allRegions
        } else {
            return allRegions.filter { it.name.contains(query.value.trim(), ignoreCase = true) }
        }
    }

    private fun handleSuccess(data: List<AreaShort>?) {
        when {
            data == null -> _regionFilterState.value = RegionFilterState.NotFound
            else -> {
                allRegions = data
                _regionFilterState.value = RegionFilterState.Content(allRegions)
            }
        }
    }

    private fun handleError(errorCode: Int?) {
        _regionFilterState.value = when (errorCode) {
            NetworkResponseStatus.NOT_FOUND -> RegionFilterState.NotFound
            NetworkResponseStatus.NO_INTERNET -> RegionFilterState.NoInternet
            NetworkResponseStatus.SERVER_ERROR -> RegionFilterState.ServerError
            else -> RegionFilterState.ServerError // или другая ошибка по умолчанию
        }
    }

    fun onSearchTextDebounce(text: String) {
        _query.value = text
    }

    fun clearSearchQuery() {
        _query.value = ""
    }

    companion object {
        const val DEBOUNCE_DELAY = 300L

        // временная заглушка для поиска регионов
        const val METHOD_STUB_COUNTRY = "Россия"
        val METHOD_STUB_AREA = null
    }
}
