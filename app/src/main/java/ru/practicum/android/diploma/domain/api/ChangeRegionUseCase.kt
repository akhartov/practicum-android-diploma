package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.AreaShort

/**
 * Бизнес-логика экрана "Выбор региона"
 * */
class ChangeRegionUseCase(
    private val filterRepository: FilterRepository,
) {
    fun setRegion(country: AreaShort?, region: AreaShort?) {
        filterRepository.setRegion(country?.name, country?.id, region?.name, region?.id)
    }
}
