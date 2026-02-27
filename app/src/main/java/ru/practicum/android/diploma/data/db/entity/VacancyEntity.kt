package ru.practicum.android.diploma.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = VacancyEntity.TABLE_NAME)
data class VacancyEntity(
    @PrimaryKey
    val vacancyId: String, // Ключ, чтобы матчить с найденой при новом поиске
    val insertTime: Long, // Время добавления в базу данных
    val vacancyName: String, // Название вакансии
    val employerCity: String? = null, // Город работодателя
    val employerName: String? = null, // Работодатель название
    val employerLogoUrl: String? = null, // Работодатель логотип
    val salaryFrom: Int? = null, // Зарплата от
    val salaryTo: Int? = null, // Зарплата до
    val salaryCurrencyCode: String? = null, // Зарплата код валюты
    val experience: String? = null, // Нет опыта
    val schedule: String? = null, // Полный день
    val employment: String? = null, // Полная занятость
    val description: String? = null, // Описание (обязанности, требования, условия)
    val skills: List<String>? = null, // Ключевые навыки,
    val url: String? = null // Ссылка для шаринга
) {
    companion object {
        const val TABLE_NAME = "vacancy_table"
    }
}
