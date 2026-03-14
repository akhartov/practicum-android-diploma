package ru.practicum.android.diploma.presentation.filter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.api.ChangeWorkplaceInteractor
import ru.practicum.android.diploma.domain.api.FiltersWatchingUseCase
import ru.practicum.android.diploma.domain.models.Filters

class WorkplaceFilterViewModel(
    private val changeWorkplaceInteractor: ChangeWorkplaceInteractor,
    filtersWatchingUseCase: FiltersWatchingUseCase,
) : ViewModel() {
    val filtersFlow: StateFlow<Filters> = filtersWatchingUseCase.filtersFlow
    fun applyWorkplace() {
        // все уже сохранено ранее при выборе
    }

    fun resetCountry() {
        changeWorkplaceInteractor.resetCountry()
    }

    fun resetArea() {
        changeWorkplaceInteractor.resetRegion()
    }
}
