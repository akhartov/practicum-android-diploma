package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.FilterIndustryDto
import ru.practicum.android.diploma.data.dto.VacancyResponse

interface VacancyApi {
    @GET("/vacancies")
    suspend fun getVacancies(@QueryMap options: Map<String, String>): VacancyResponse

    @GET("/areas")
    suspend fun getFilterArea(): List<FilterAreaDto>

    @GET("/industries")
    suspend fun getFilterIndustry(): List<FilterIndustryDto>

    companion object {
        const val HOST_URL = "https://practicum-diploma-8bc38133faba.herokuapp.com"
    }
}
