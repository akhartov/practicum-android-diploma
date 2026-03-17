package ru.practicum.android.diploma.data.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.api.IndustrySettingsRepository
import ru.practicum.android.diploma.domain.models.Industry

class IndustrySettingsRepositoryImpl(
    private val filterRepository: FilterRepository
) : IndustrySettingsRepository {
    private val _selectedIndustry = MutableStateFlow<Industry?>(null)
    override val selectedIndustryFlow: StateFlow<Industry?> = _selectedIndustry.asStateFlow()

    override fun selectIndustry(industry: Industry?) {
        _selectedIndustry.value = industry
    }

    override fun applySelectedIndustry() {
        filterRepository.setFilters(
            filterRepository.getFilters().copy(
                industryId = _selectedIndustry.value?.id,
                industryName = _selectedIndustry.value?.name
            )
        )
        clearSelectedIndustry()
    }

    override fun clearSelectedIndustry() {
        _selectedIndustry.value = null
    }
}
