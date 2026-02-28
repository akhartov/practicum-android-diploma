package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.api.SearchVacanciesInteractor
import ru.practicum.android.diploma.domain.converters.VacancyMapper
import ru.practicum.android.diploma.domain.impl.SearchVacanciesInteractorImpl

val domainModule = module {
    single { VacancyMapper(get()) }

    single<SearchVacanciesInteractor> {
        SearchVacanciesInteractorImpl(get())
    }
}
