package ru.practicum.android.diploma.presentation.filter.region

import ru.practicum.android.diploma.domain.models.Region

sealed interface RegionFilterState {
    object NotFound : RegionFilterState
    object NoInternet : RegionFilterState
    object ServerError : RegionFilterState
    object Loading : RegionFilterState
    data class Content(val regions: List<Region>) : RegionFilterState
}
