package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.IndustryInteractor
import ru.practicum.android.diploma.domain.api.IndustryRepository
import ru.practicum.android.diploma.domain.models.IndustryResponse
import ru.practicum.android.diploma.util.Resource

class IndustryInteractorImpl(private val industryRepository: IndustryRepository) : IndustryInteractor {
    override fun getIndustries(): Flow<Resource<IndustryResponse>> {
        return industryRepository.getIndustries()
    }
}
