package ru.practicum.android.diploma.presentation.filter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.api.ChangeWorkplaceUseCase
import ru.practicum.android.diploma.domain.models.Area

class WorkplaceFilterViewModel(
    private val changeWorkplaceUseCase: ChangeWorkplaceUseCase
) : ViewModel() {
    val area: StateFlow<Area?> = changeWorkplaceUseCase.area
    val country: StateFlow<Area?> = changeWorkplaceUseCase.country
    fun applyWorkplace() {
        changeWorkplaceUseCase.applyWorkplace()
    }
}
