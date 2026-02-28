package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.api.SearchVacanciesInteractor
import ru.practicum.android.diploma.domain.impl.SearchVacanciesInteractorImpl

val domainModule = module {
    single<SearchVacanciesInteractor> {
        SearchVacanciesInteractorImpl(get())
    }
}
