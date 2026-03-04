package ru.practicum.android.diploma.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.practicum.android.diploma.domain.models.Phones

@Entity(tableName = VacancyEntity.TABLE_NAME)
data class VacancyEntity(
    @PrimaryKey
    val vacancyId: String, // Ключ, чтобы матчить с найденой при новом поиске
    val insertTime: Long, // Время добавления в базу данных
    val vacancyName: String, // Название вакансии
    val employerCity: String? = null, // Город работодателя
    val address: String? = null, // Если адреса нет, то должно отображаться название региона
    val employerName: String? = null, // Работодатель название
    val employerLogoUrl: String? = null, // Работодатель логотип
    val salaryString: String? = null, // Зарплата от X до Y Руб
    val experience: String? = null, // Нет опыта
    val schedule: String? = null, // Полный день
    val employment: String? = null, // Полная занятость
    val description: String? = null, // Описание (обязанности, требования, условия)
    val skills: List<String>? = null, // Ключевые навыки,
    val contactsName: String? = null,
    val contactsEmail: String? = null,
    val contactsPhones: List<Phones>? = null,
    val url: String? = null // Ссылка для шаринга
) {
    companion object {
        const val TABLE_NAME = "vacancy_table"
    }
}
