package ru.practicum.android.diploma.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.converters.VacancyMapper
import ru.practicum.android.diploma.data.dto.vacancy.VacancyDto
import ru.practicum.android.diploma.data.network.VacancyApiClient
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

class VacancyRepositoryImpl(
    private val apiClient: VacancyApiClient,
    private val mapper: VacancyMapper
) : VacancyRepository {
    override fun getVacancy(id: String): Flow<Resource<Vacancy>> = flow {
        val response = apiClient.getVacancyById(id)
        when (response.resultCode) {
            NetworkResponseStatus.NO_INTERNET -> {
                emit(Resource.Error(NetworkResponseStatus.NO_INTERNET))
            }

            NetworkResponseStatus.SUCCESS -> {
                emit(Resource.Success(mapper.mapToVancancy(response as VacancyDto)))
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
