package ru.practicum.android.diploma.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.FavoritesInteractor

class FavoritesViewModel(
    private val favoritesInteractor: FavoritesInteractor,
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _stateFlow = MutableStateFlow(FavoritesState())
    fun stateFlow(): StateFlow<FavoritesState> = _stateFlow.asStateFlow()

    init {
        loadMoreItems()
    }

    fun loadMoreItems() {
        viewModelScope.launch {
            _isLoading.value = true
            runCatching {
                val newItems = favoritesInteractor.getNewVacancies(DEFAULT_PAGE_SIZE)
                _stateFlow.value = FavoritesState(_stateFlow.value.vacancies + newItems.toMutableList())
            }.onFailure {
                _stateFlow.value = FavoritesState(_stateFlow.value.vacancies, true)
            }

            _isLoading.value = false

        }
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 15
    }
}
