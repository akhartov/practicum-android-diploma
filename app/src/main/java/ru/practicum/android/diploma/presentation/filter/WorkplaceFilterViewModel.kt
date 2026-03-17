package ru.practicum.android.diploma.presentation.filter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.api.ChangeWorkplaceInteractor
import ru.practicum.android.diploma.domain.models.AreaShort

class WorkplaceFilterViewModel(
    private val changeWorkplaceInteractor: ChangeWorkplaceInteractor,
) : ViewModel() {
    val selectedCountry: StateFlow<AreaShort?> = changeWorkplaceInteractor.selectedCountry
    val selectedRegion: StateFlow<AreaShort?> = changeWorkplaceInteractor.selectedRegion
    fun applyWorkplace() {
        changeWorkplaceInteractor.applyWorkplace()
    }

    fun resetSelectedCountry() {
        changeWorkplaceInteractor.resetSelectedCountry()
    }

    fun resetSelectedRegion() {
        changeWorkplaceInteractor.resetSelectedRegion()
    }

    fun cleanSelectedWorkplace() {
        changeWorkplaceInteractor.cleanSelectedWorkplace()
    }
}
