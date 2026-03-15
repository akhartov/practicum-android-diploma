package ru.practicum.android.diploma.domain.api

/**
 * Бизнес-логика окна "Настройки фильтрации"
 * */
class ChangeFilterSettingsInteractor(
    private val filterSettingsRepository: FilterSettingsRepository
) {
    /**
     * Очистка поля "Место работы"
     * */
    fun resetWorkplace() {
        filterSettingsRepository.resetWorkplace()
    }

    /**
     * Очистка поля "Отрасль"
     * */
    fun resetIndustry() {
        filterSettingsRepository.resetIndustry()
    }

    fun changeWithSalaryOnly() {
        filterSettingsRepository.changeWithSalaryOnly()
    }

    fun setSalary(salary: String) {
        filterSettingsRepository.setSalary(salary)
    }
}
