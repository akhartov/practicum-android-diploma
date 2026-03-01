package ru.practicum.android.diploma.presentation.model

import ru.practicum.android.diploma.domain.models.Filters

sealed interface FilterIconState {
    object isLoading : FilterIconState // фильтры применены(если они активны) идёт поиск вакансий
    object noActiveFilter : FilterIconState // фильтров нет, поиск вакансий завершён
    class isActiveFilter(val filters: Filters) : FilterIconState // фильтры применены поиск вакансий завершён
}
