package ru.practicum.android.diploma.domain.api

/**
 * Бизнес-логика экрана "Место ралоты"
 * */
class ChangeWorkplaceInteractor(
    private val filterRepository: FilterRepository,
) {
    /**
     * Очистка ранее выбранной страны
     * */
    fun resetCountry() {
        filterRepository.setCountry(countryName = null, countryId = null)
    }

    /**
     * Очистка ранее выбранного региона
     * */
    fun resetRegion() {
        filterRepository.setRegion(countryName = null, countryId = null, regionName = null, regionId = null)
    }
}
