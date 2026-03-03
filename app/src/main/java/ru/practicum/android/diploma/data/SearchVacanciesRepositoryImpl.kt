package ru.practicum.android.diploma.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.dto.VacancyResponse
import ru.practicum.android.diploma.data.dto.vacancy.VacancyDto
import ru.practicum.android.diploma.data.network.VacancyApiClient
import ru.practicum.android.diploma.domain.api.SearchVacanciesRepository
import ru.practicum.android.diploma.domain.converters.VacancyMapper
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyShortResponse
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

class SearchVacanciesRepositoryImpl(
    private val apiClient: VacancyApiClient,
    private val mapper: VacancyMapper,
) : SearchVacanciesRepository {
    override fun searchVacancies(
        options: HashMap<String, String>
    ): Flow<Resource<VacancyShortResponse>> =
        flow {
            val response = apiClient.getVacancies(options)
            when (response.resultCode) {
                NetworkResponseStatus.NO_INTERNET -> {
                    emit(Resource.Error(NetworkResponseStatus.NO_INTERNET))
                }

                NetworkResponseStatus.SUCCESS -> {
                    emit(Resource.Success(mapper.mapResponse(response as VacancyResponse)))
                }

                NetworkResponseStatus.NOT_FOUND -> {
                    emit(Resource.Error(NetworkResponseStatus.NOT_FOUND))
                }

                else -> {
                    emit(Resource.Error(NetworkResponseStatus.SERVER_ERROR))
                }
            }
        }
}
