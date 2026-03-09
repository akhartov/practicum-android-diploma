package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.AreaShort
import ru.practicum.android.diploma.domain.models.Workplace

class ChangeWorkplaceUseCase(
    private val workplaceRepository: WorkplaceCachingRepository,
    private val filterRepository: FilterCachingRepository,
) {
    val area: StateFlow<AreaShort?> = workplaceRepository.area
    val country: StateFlow<AreaShort?> = workplaceRepository.country

    fun applyWorkplace() {
        buildWorkplace(
            workplaceRepository.country.value,
            workplaceRepository.area.value,
        )?.let { workplace ->
            filterRepository.cacheWorkplace(workplace)
        }
    }

    private fun buildWorkplace(country: AreaShort?, area: AreaShort?): Workplace? {
        return when {
            area == null && country == null -> null
            area == null && country != null -> Workplace(country.name, country.id)
            area != null && country != null -> Workplace("${country.name}, ${area.name}", area.id)
            area != null && country == null -> Workplace(area.name, area.id)
            else -> null
        }
    }
}
