package ru.practicum.android.diploma.di

import org.koin.dsl.module

val domainModule = module {
    single { VacancyMapper(get(), get()) }
    single { DescriptionSplitter() }
    single<SearchVacanciesInteractor> {
        SearchVacanciesInteractorImpl(get())
    }
}
