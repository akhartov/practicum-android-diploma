package ru.practicum.android.diploma.presentation.filter.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.AreaInteractor
import ru.practicum.android.diploma.domain.api.ChangeCountryUseCase
import ru.practicum.android.diploma.domain.models.AreaShort
import ru.practicum.android.diploma.util.NetworkResponseStatus
import ru.practicum.android.diploma.util.Resource

class CountryFilterViewModel(
    private val areaInteractor: AreaInteractor,
    private val changeCountryUseCase: ChangeCountryUseCase,
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

    fun setCountry(country: AreaShort?) {
        changeCountryUseCase.setCountry(country)
    }

    private fun proccesResult(result: Resource<List<AreaShort>>) {
        when (result) {
            is Resource.Success -> {
                handleSuccess(result.data)
            }
            is Resource.Error -> {
                handleError(result.error)
            }
        }
    }

    private fun handleSuccess(data: List<AreaShort>?) {
        when {
            data == null -> _countryFilterState.value = CountryFilterState.NotFound
            else -> {
                _countryFilterState.value = CountryFilterState.Content(data.moveOtherRegionsToEnd())
            }
        }
    }

    private fun handleError(errorCode: Int?) {
        _countryFilterState.value = when (errorCode) {
            NetworkResponseStatus.NOT_FOUND -> CountryFilterState.NotFound
            NetworkResponseStatus.NO_INTERNET -> CountryFilterState.NoInternet
            NetworkResponseStatus.SERVER_ERROR -> CountryFilterState.ServerError
            else -> CountryFilterState.ServerError // или другая ошибка по умолчанию
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
