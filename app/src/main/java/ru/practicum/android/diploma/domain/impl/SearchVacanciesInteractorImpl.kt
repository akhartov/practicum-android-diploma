package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.SearchVacanciesInteractor
import ru.practicum.android.diploma.domain.api.SearchVacanciesRepository
import ru.practicum.android.diploma.domain.models.VacancyShortResponse
import ru.practicum.android.diploma.util.Resource

class SearchVacanciesInteractorImpl(private val searchRepository: SearchVacanciesRepository) :
    SearchVacanciesInteractor {
    override fun searchVacancies(options: HashMap<String, String>): Flow<Resource<VacancyShortResponse>> {
        return searchRepository.searchVacancies(options)
    }
}
