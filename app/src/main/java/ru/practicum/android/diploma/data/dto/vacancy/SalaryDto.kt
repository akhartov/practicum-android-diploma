package ru.practicum.android.diploma.data.dto.vacancy

import ru.practicum.android.diploma.domain.models.Salary

data class SalaryDto(
    val id: Int,
    val currency: String? = null,
    val from: Int? = null,
    val to: Int? = null,
)

fun SalaryDto.toSalary(): Salary {
    return Salary(
        from = this.from,
        to = this.to,
        currency = this.currency
    )
}
