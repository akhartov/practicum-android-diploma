package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.converters.DescriptionSplitter
import ru.practicum.android.diploma.domain.converters.VacancyMapper

val domainModule = module {
    single { VacancyMapper(get()) }
    single { DescriptionSplitter() }
    single<SearchVacanciesInteractor> {
        SearchVacanciesInteractorImpl(get())
    }
}
