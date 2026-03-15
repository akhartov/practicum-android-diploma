package ru.practicum.android.diploma.presentation.filter.region

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.AreaInteractor
import ru.practicum.android.diploma.domain.api.ChangeRegionUseCase
import ru.practicum.android.diploma.domain.models.Region
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

@OptIn(FlowPreview::class)
class RegionFilterViewModel(
    private val areaInteractor: AreaInteractor,
    private val changeRegionUseCase: ChangeRegionUseCase,
) : ViewModel() {
    private val _regionFilter = MutableStateFlow<RegionFilterState>(RegionFilterState.Loading)
    val regionFilter: StateFlow<RegionFilterState> = _regionFilter.asStateFlow()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    var allRegions = listOf<Region>()

    init {
        viewModelScope.launch {
            _regionFilter.value = RegionFilterState.Loading
            areaInteractor.getRegions(changeRegionUseCase.selectedCountry.value?.id)
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
            query.debounce(DEBOUNCE_DELAY) // пауза
                .distinctUntilChanged() // ограничиваем если есть новый поиск
                .onEach {
                    when (_regionFilter.value) {
                        is RegionFilterState.Content, RegionFilterState.NotFound -> {
                            val filteredRegions = getFilteredRegions()

                            _regionFilter.value = when {
                                filteredRegions.isEmpty() -> RegionFilterState.NotFound
                                else -> RegionFilterState.Content(filteredRegions)
                            }
                        }

                        else -> {}
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun getFilteredRegions(): List<Region> {
        val trimmedQuery = query.value.trim()
        return if (trimmedQuery.isEmpty()) {
            allRegions
        } else {
            return allRegions.filter { it.region.name.contains(query.value.trim(), ignoreCase = true) }
        }
    }

    private fun handleSuccess(data: List<Region>?) {
        when {
            data == null -> _regionFilter.value = RegionFilterState.NotFound
            else -> {
                allRegions = data
                _regionFilter.value = RegionFilterState.Content(allRegions)
            }
        }
    }

    private fun handleError(errorCode: Int?) {
        _regionFilter.value = when (errorCode) {
            NetworkResponseStatus.NOT_FOUND -> RegionFilterState.NotFound
            NetworkResponseStatus.NO_INTERNET -> RegionFilterState.NoInternet
            NetworkResponseStatus.SERVER_ERROR -> RegionFilterState.ServerError
            else -> RegionFilterState.ServerError // или другая ошибка по умолчанию
        }
    }

    fun setRegion(region: Region) {
        changeRegionUseCase.selectRegion(
            region.parentCountry,
            region.region,
        )
    }

    fun onSearchTextDebounce(text: String) {
        _query.value = text
    }

    fun clearSearchQuery() {
        _query.value = ""
    }

    companion object {
        const val DEBOUNCE_DELAY = 300L
    }
}
