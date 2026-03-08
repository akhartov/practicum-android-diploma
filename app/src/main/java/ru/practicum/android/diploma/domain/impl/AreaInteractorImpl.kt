package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.domain.api.AreaInteractor
import ru.practicum.android.diploma.domain.api.AreaRepository
import ru.practicum.android.diploma.domain.models.AreaShort
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

class AreaInteractorImpl(
    private val areaRepository: AreaRepository
) : AreaInteractor {
    override fun getCountries(): Flow<Resource<List<AreaShort>>> =
        areaRepository.getAreas().map { resource ->
            when (resource) {
                is Resource.Success -> {
                    val countries = resource.data
                        ?.filter { area -> area.parentId == null }
                        ?.map { area ->
                            AreaShort(
                                id = area.id,
                                name = area.name
                            )
                        } ?: emptyList()
                    Resource.Success(countries)
                }
                is Resource.Error -> {
                    Resource.Error(resource.error ?: NetworkResponseStatus.SERVER_ERROR)
                }
            }
        }

    // Если нужно полный списко регионов страны, то в параметры regionName передаём null
    // Если нужно найти конкретный регион в стране, то передаём его имя
    override fun getRegions(countryName: String?, regionName: String?): Flow<Resource<List<AreaShort>>> =
        areaRepository.getAreas().map { resource ->
            when (resource) {
                is Resource.Success -> {
                    var parentCountryName = ""
                    val area = resource.data
                        ?.find { country ->
                            parentCountryName = country.name
                            countryName.isNullOrEmpty() || country.name.equals(countryName)
                        }
                        ?.areas
                        ?.filter { region ->
                            regionName.isNullOrEmpty() || region.name.contains(regionName, ignoreCase = true)
                        }
                        ?.map { region ->
                            AreaShort(
                                parentName = parentCountryName,
                                id = region.id,
                                name = region.name
                            )
                        } ?: emptyList()

                    Resource.Success(area)
                }
                is Resource.Error -> {
                    Resource.Error(resource.error ?: NetworkResponseStatus.SERVER_ERROR)
                }
            }
        }

}
