package ru.practicum.android.diploma.data.network

import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.FilterIndustryDto
import ru.practicum.android.diploma.data.dto.VacancyResponse

interface VacancyApiClient {
    suspend fun getVacancies(options: Map<String, String>): VacancyResponse

    suspend fun getFilterAreas(): List<FilterAreaDto>

    suspend fun getFilterIndustry(): List<FilterIndustryDto>
}
