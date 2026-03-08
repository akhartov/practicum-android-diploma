package ru.practicum.android.diploma.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.converters.FiltersMapper
import ru.practicum.android.diploma.data.dto.AreaResponse
import ru.practicum.android.diploma.data.network.VacancyApiClient
import ru.practicum.android.diploma.domain.api.AreaRepository
import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

class AreaRepositoryImpl(
    private val apiClient: VacancyApiClient,
    private val mapper: FiltersMapper
) : AreaRepository {
    override fun getAreas(): Flow<Resource<List<Area>>> =
        flow {
            val response = apiClient.getFilterAreas()
            when (response.resultCode) {
                NetworkResponseStatus.NO_INTERNET -> {
                    emit(Resource.Error(NetworkResponseStatus.NO_INTERNET))
                }

                NetworkResponseStatus.SUCCESS -> {
                    emit(
                        Resource.Success(
                            data = (response as AreaResponse).areas.map {
                                mapper.mapToArea(it)
                            }
                        )
                    )
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
