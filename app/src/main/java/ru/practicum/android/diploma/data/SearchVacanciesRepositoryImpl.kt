package ru.practicum.android.diploma.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.dto.VacancyResponse
import ru.practicum.android.diploma.data.dto.toVacancyShortResponse
import ru.practicum.android.diploma.data.dto.vacancy.VacancyDetailDto
import ru.practicum.android.diploma.data.dto.vacancy.toVacancy
import ru.practicum.android.diploma.data.network.VacancyApiClient
import ru.practicum.android.diploma.domain.api.SearchVacanciesRepository
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyShortResponse
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

class SearchVacanciesRepositoryImpl(
    private val apiClient: VacancyApiClient
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
                    val data = (response as VacancyResponse).toVacancyShortResponse()
                    emit(Resource.Success(data))
                }

                else -> {
                    emit(Resource.Error(NetworkResponseStatus.SERVER_ERROR))
                }
            }
        }

    override fun getVacancy(id: String): Flow<Resource<Vacancy>> = flow {
        val response = apiClient.getVacancyById(id)
        when (response.resultCode) {
            NetworkResponseStatus.NO_INTERNET -> {
                emit(Resource.Error(NetworkResponseStatus.NO_INTERNET))
            }

            NetworkResponseStatus.SUCCESS -> {
                val data = (response as VacancyDetailDto).toVacancy()
                emit(Resource.Success(data))
            }

            else -> {
                emit(Resource.Error(NetworkResponseStatus.SERVER_ERROR))
            }
        }
    }
}
