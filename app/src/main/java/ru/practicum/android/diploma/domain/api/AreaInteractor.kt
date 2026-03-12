package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.AreaShort
import ru.practicum.android.diploma.domain.models.Region
import ru.practicum.android.diploma.util.Resource

interface AreaInteractor {
    fun getCountries(): Flow<Resource<List<AreaShort>>>
    fun getRegions(countryId: Int?): Flow<Resource<List<Region>>>
}
