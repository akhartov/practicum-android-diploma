package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.Filters

/**
 * Класс для получения состояния сохраненного значения фильтра
 * */
class FiltersWatchingUseCase(
    filterRepository: FilterRepository
) {
    val filtersFlow: StateFlow<Filters> = filterRepository.filtersFlow
}
