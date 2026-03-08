package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.domain.models.Workplace

class ChangeWorkplaceUseCase(
    private val workplaceRepository: WorkplaceCachingRepository,
    private val filterRepository: FilterCachingRepository,
) {
    val area: StateFlow<Area?> = workplaceRepository.area
    val country: StateFlow<Area?> = workplaceRepository.country

    fun applyWorkplace() {
        buildCombinedName(
            workplaceRepository.country.value?.name,
            workplaceRepository.area.value?.name
        )?.let { workplaceName ->
            workplaceRepository.area.value?.id.let { areaId ->
                filterRepository.cacheWorkplace(Workplace(workplaceName, areaId))
            }
        }
    }

    private fun buildCombinedName(countryName: String?, areaName: String?): String? {
        return when {
            !countryName.isNullOrBlank() && !areaName.isNullOrBlank() -> "$countryName, $areaName"
            !areaName.isNullOrBlank() -> areaName
            !countryName.isNullOrBlank() -> countryName
            else -> null
        }
    }
}
