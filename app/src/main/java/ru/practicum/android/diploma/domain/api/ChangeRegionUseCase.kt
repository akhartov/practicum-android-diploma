package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.AreaShort

/**
 * Бизнес-логика экрана "Выбор региона"
 * Здесь ничего не сохраняется в фильтр, только выбираем регион
 * Выбор региона может быть ограничен страной, поэтому нужно учитывать страну по selectedCountry
 * */
class ChangeRegionUseCase(
    private val workspaceCachingRepository: WorkspaceCachingRepository,
) {
    val selectedCountry: StateFlow<AreaShort?> = workspaceCachingRepository.selectedCountry

    fun selectRegion(country: AreaShort?, region: AreaShort?) {
        workspaceCachingRepository.selectRegion(country, region)
    }
}
