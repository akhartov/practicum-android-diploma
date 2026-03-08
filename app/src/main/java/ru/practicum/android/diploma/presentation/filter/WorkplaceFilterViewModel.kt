package ru.practicum.android.diploma.presentation.filter

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.domain.api.ChangeWorkplaceUseCase

class WorkplaceFilterViewModel(
    private val changeWorkplaceUseCase: ChangeWorkplaceUseCase
) : ViewModel() {
    fun applyWorkplace() {
        changeWorkplaceUseCase.applyWorkplace()
    }
}
