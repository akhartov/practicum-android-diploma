package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.AreaShort

/**
 * Бизнес-логика экрана "Место ралоты"
 * */
class ChangeWorkplaceInteractor(
    private val workspaceCachingRepository: WorkspaceCachingRepository,
) {
    val selectedCountry: StateFlow<AreaShort?> = workspaceCachingRepository.selectedCountry
    val selectedRegion: StateFlow<AreaShort?> = workspaceCachingRepository.selectedRegion

    /**
     * Сохраняем выбранное в фильтр
     * */
    fun applyWorkplace() {
        workspaceCachingRepository.applyWorkplace()
    }

    /**
     * Очистка того, что выбрали, но не применили
     * Смена региона сбрасыает и страну
     * */
    fun cleanSelectedWorkplace() {
        resetSelectedRegion()
    }

    /**
     * Очистка ранее выбранной страны
     * */
    fun resetSelectedCountry() {
        workspaceCachingRepository.selectCountry(null)
    }

    /**
     * Очистка ранее выбранного региона
     * */
    fun resetSelectedRegion() {
        workspaceCachingRepository.selectRegion(null, null)
    }
}
