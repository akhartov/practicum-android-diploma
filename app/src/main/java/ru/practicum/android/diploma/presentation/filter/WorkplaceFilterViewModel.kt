package ru.practicum.android.diploma.presentation.filter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.api.ChangeWorkplaceUseCase
import ru.practicum.android.diploma.domain.models.AreaShort

class WorkplaceFilterViewModel(
    private val changeWorkplaceUseCase: ChangeWorkplaceUseCase
) : ViewModel() {
    val area: StateFlow<AreaShort?> = changeWorkplaceUseCase.area
    val country: StateFlow<AreaShort?> = changeWorkplaceUseCase.country
    fun applyWorkplace() {
        changeWorkplaceUseCase.applyWorkplace()
    }

    fun resetWorkspace() {
        changeWorkplaceUseCase.resetWorkspace()
    }
}
