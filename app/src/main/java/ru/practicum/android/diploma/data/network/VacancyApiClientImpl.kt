package ru.practicum.android.diploma.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.FilterIndustryDto
import ru.practicum.android.diploma.data.dto.VacancyResponse
import ru.practicum.android.diploma.data.dto.vacancy.VacancyDetailDto

class VacancyApiClientImpl(private val api: VacancyApi) : VacancyApiClient {

    override suspend fun getVacancies(options: Map<String, String>): VacancyResponse {
        return withContext(Dispatchers.IO) {
            api.getVacancies(options)
        }
    }

    override suspend fun getVacancyById(id: String): VacancyDetailDto {
        return withContext(Dispatchers.IO) {
            api.getVacancyById(id)
        }
    }

    override suspend fun getFilterAreas(): List<FilterAreaDto> {
        return withContext(Dispatchers.IO) {
            api.getFilterAreas()
        }
    }

    override suspend fun getFilterIndustries(): List<FilterIndustryDto> {
        return withContext(Dispatchers.IO) {
            api.getFilterIndustries()
        }
    }
}
