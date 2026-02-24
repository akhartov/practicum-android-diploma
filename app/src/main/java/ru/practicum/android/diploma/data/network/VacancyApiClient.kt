package ru.practicum.android.diploma.data.network

import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.FilterIndustryDto
import ru.practicum.android.diploma.data.dto.VacancyResponse
import ru.practicum.android.diploma.data.dto.vacancy.VacancyDetailDto

interface VacancyApiClient {
    suspend fun getVacancies(options: Map<String, String>): VacancyResponse
    suspend fun getVacancyById(id: String): VacancyDetailDto

    suspend fun getFilterAreas(): List<FilterAreaDto>

    suspend fun getFilterIndustries(): List<FilterIndustryDto>
}
