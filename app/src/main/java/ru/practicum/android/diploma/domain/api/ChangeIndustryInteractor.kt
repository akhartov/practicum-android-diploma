package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Industry

/**
 * Бизнес-логика экрана "Выбор отрасли"
 * */
class ChangeIndustryInteractor(
    private val industrySettingsRepository: IndustrySettingsRepository
) {
    val selectedIndustryFlow: StateFlow<Industry?> = industrySettingsRepository.selectedIndustryFlow

    /**
     * Выбор отрасли из списка
     * */
    fun selectIndustry(industry: Industry?) {
        industrySettingsRepository.selectIndustry(industry)
    }

    /**
     * Сохранение выбранной отрасли в фильтре
     * */
    fun applySelectedIndustry() {
        industrySettingsRepository.applySelectedIndustry()
    }

    /**
     * Очистка выбранной отрасли, например при выходе
     * */
    fun clearSelectedIndustry() {
        industrySettingsRepository.clearSelectedIndustry()
    }
}
