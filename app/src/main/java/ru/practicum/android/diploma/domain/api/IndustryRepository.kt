package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.IndustryResponse
import ru.practicum.android.diploma.util.Resource

interface IndustryRepository {
    fun getIndustries(): Flow<Resource<IndustryResponse>>
}
