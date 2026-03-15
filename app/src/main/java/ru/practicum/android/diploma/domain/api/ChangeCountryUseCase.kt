package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.AreaShort

/**
 * Бизнес-логика экрана "Выбор страны"
 * Здесь ничего не сохраняется в фильтр, только выбираем страну
 * */
class ChangeCountryUseCase(
    private val workspaceCachingRepository: WorkspaceCachingRepository,
) {
    fun setCountry(country: AreaShort?) {
        workspaceCachingRepository.selectCountry(country)
    }
}
