package ru.practicum.android.diploma.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.converters.IndustryMapper
import ru.practicum.android.diploma.data.dto.IndustryDtoResponse
import ru.practicum.android.diploma.data.network.VacancyApiClient
import ru.practicum.android.diploma.domain.api.IndustryRepository
import ru.practicum.android.diploma.domain.models.IndustryResponse
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

class IndustryRepositoryImpl(
    private val apiClient: VacancyApiClient,
    private val mapper: IndustryMapper
) : IndustryRepository {
    override fun getIndustries(): Flow<Resource<IndustryResponse>> =
        flow {
            val response = apiClient.getFilterIndustries()
            Log.e("resultCode", response.resultCode.toString())
            when (response.resultCode) {
                NetworkResponseStatus.NO_INTERNET -> {
                    emit(Resource.Error(NetworkResponseStatus.NO_INTERNET))
                }

                NetworkResponseStatus.SUCCESS -> {
                    emit(Resource.Success(mapper.mapResponse(response as IndustryDtoResponse)))
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
