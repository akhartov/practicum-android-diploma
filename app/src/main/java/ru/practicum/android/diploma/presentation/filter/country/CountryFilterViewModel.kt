package ru.practicum.android.diploma.presentation.filter.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.AreaInteractor
import ru.practicum.android.diploma.domain.models.AreaShort
import ru.practicum.android.diploma.util.Resource

class CountryFilterViewModel(
    private val areaInteractor: AreaInteractor
) : ViewModel() {
    private val _countryFilterState = MutableStateFlow<CountryFilterState>(CountryFilterState.Loading)
    val countryFilterState: StateFlow<CountryFilterState> = _countryFilterState.asStateFlow()

    init {
        viewModelScope.launch {
            areaInteractor.getCountries().collect { result ->
                proccesResult(result)
            }
        }
    }

    private fun proccesResult(result: Resource<List<AreaShort>>) {
        when (result) {
            is Resource.Success -> {
                _countryFilterState.value = CountryFilterState.Content(
                    result.data?.moveOtherRegionsToEnd() ?: emptyList()
                )
            }
            is Resource.Error -> {
                _countryFilterState.value = CountryFilterState.Fail
            }
        }
    }

    fun List<AreaShort>.moveOtherRegionsToEnd(): List<AreaShort> {
        val (other, rest) = partition { it.name == OTHER_REGION }
        return rest + other
    }

    companion object {
        private const val OTHER_REGION = "Другие регионы"
    }
}
