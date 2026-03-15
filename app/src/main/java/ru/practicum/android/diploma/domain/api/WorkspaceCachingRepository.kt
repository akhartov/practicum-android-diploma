package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.models.AreaShort

class WorkspaceCachingRepository(
    private val filterRepository: FilterRepository
) {
    private val _selectedCountry = MutableStateFlow<AreaShort?>(null)
    private val _selectedRegion = MutableStateFlow<AreaShort?>(null)

    val selectedCountry: StateFlow<AreaShort?> = _selectedCountry
    val selectedRegion: StateFlow<AreaShort?> = _selectedRegion

    fun selectCountry(country: AreaShort?) {
        _selectedCountry.value = country
        _selectedRegion.value = null
    }

    fun selectRegion(country: AreaShort?, region: AreaShort?) {
        _selectedCountry.value = country
        _selectedRegion.value = region
    }

    fun applyWorkplace() {
        filterRepository.setRegion(
            _selectedCountry.value?.name,
            _selectedCountry.value?.id,
            _selectedRegion.value?.name,
            _selectedRegion.value?.id
        )

        selectRegion(null, null)
    }
}
