package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.AreaShort

/**
 * Бизнес-логика экрана "Выбор страны"
 * */
class ChangeCountryUseCase(
    private val filterRepository: FilterRepository
) {
    fun setCountry(country: AreaShort?) {
        filterRepository.setCountry(country?.name, country?.id)
    }
}
