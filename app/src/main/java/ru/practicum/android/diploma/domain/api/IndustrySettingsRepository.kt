package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Industry

/**
 * Отвечает за хранение состояния экрана "Выбор отрасли"
 * */
interface IndustrySettingsRepository {
    /**
     * Выбранная из списка "Отрасль"
     * */
    val selectedIndustryFlow: StateFlow<Industry?>

    /**
     * Сохранение выбранной отрасли в оперативной памяти
     * */
    fun selectIndustry(industry: Industry?)

    /**
     * Сохранение выбранной отрасли в настройках фильтра
     * */
    fun applySelectedIndustry()

    /**
     * Очистка выбранной отрасли в оперативной памяти
     * */
    fun clearSelectedIndustry()
}
