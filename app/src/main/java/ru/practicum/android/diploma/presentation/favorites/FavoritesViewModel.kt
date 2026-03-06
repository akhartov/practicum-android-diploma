package ru.practicum.android.diploma.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.GetVacanciesFlowUseCase

class FavoritesViewModel(
    private val getVacanciesFlowUseCase: GetVacanciesFlowUseCase,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<FavoritesState?>(null)
    fun stateFlow(): StateFlow<FavoritesState?> = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                getVacanciesFlowUseCase.getVacanciesFlow().collect { vacancies ->
                    _stateFlow.emit(FavoritesState.Content(vacancies))
                }
            }.onFailure {
                _stateFlow.emit(FavoritesState.Fail)
            }
        }
    }
}
