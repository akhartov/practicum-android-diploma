package ru.practicum.android.diploma.data.dto.vacancy

import ru.practicum.android.diploma.domain.models.Schedule

data class ScheduleDto(
    val id: String,
    val name: String,
)

fun ScheduleDto.toSchedule(): Schedule {
    return Schedule(id = this.id, name = this.name)
}
