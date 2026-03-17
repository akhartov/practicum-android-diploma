package ru.practicum.android.diploma.domain.api

/**
 * Управление состоянием фильтра с экрана "Настройки фильтрации"
 * */
interface FilterSettingsRepository {
    /**
     * Очистка сохранной отрасли
     * */
    fun resetIndustry()

    /**
     * Очистка сохранного места работы
     * */
    fun resetWorkplace()

    fun changeWithSalaryOnly()

    fun setSalary(salary: String)
}
