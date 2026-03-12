package ru.practicum.android.diploma.data.network

import ru.practicum.android.diploma.data.Response

interface VacancyApiClient {
    suspend fun getVacancies(options: Map<String, String>): Response

    suspend fun getVacancyById(id: String): Response

    suspend fun getFilterAreas(): Response

    suspend fun getFilterIndustries(): Response
}
