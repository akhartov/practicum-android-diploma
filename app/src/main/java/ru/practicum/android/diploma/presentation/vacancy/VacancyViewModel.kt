package ru.practicum.android.diploma.presentation.vacancy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.VacancyInteractor
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.presentation.model.VacancyDetailsState
import ru.practicum.android.diploma.util.NetworkResponseStatus

class VacancyViewModel(private val vacancyInteractor: VacancyInteractor) : ViewModel() {
    private val _state = MutableStateFlow<VacancyDetailsState>(VacancyDetailsState.Loading)
    val state: StateFlow<VacancyDetailsState> = _state.asStateFlow()

    fun getVacancy(id: String) {
        _state.value = VacancyDetailsState.Loading
        viewModelScope.launch {
            vacancyInteractor.getVacancyById(id)
                .collect { resource ->
                    processResult(resource.data, resource.error)
                }
        }
    }

    private fun processResult(vacancy: Vacancy?, error: Int?) {
        if (error != null) {
            when (error) {
                NetworkResponseStatus.NO_INTERNET -> _state.value = VacancyDetailsState.NoInternet
                NetworkResponseStatus.NOT_FOUND -> _state.value = VacancyDetailsState.NotFound
                else -> _state.value = VacancyDetailsState.ServerError
            }
        } else if (vacancy != null) {
            _state.value = VacancyDetailsState.Content(vacancy)
        }
    }
}
