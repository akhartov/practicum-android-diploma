package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.data.dto.AreaResponse
import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.FilterIndustryDto
import ru.practicum.android.diploma.data.dto.VacancyResponse
import ru.practicum.android.diploma.data.dto.vacancy.VacancyDto

interface VacancyApi {
    @GET("/vacancies")
    suspend fun getVacancies(@QueryMap options: Map<String, String>): VacancyResponse

    @GET("/vacancies/{id}")
    suspend fun getVacancyById(@Path("id") id: String): VacancyDto

    @GET("/areas")
    suspend fun getFilterAreas(): AreaResponse

    @GET("/industries")
    suspend fun getFilterIndustries(): List<FilterIndustryDto>

    companion object {
        const val HOST_URL = "https://practicum-diploma-8bc38133faba.herokuapp.com"
    }
}
