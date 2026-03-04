package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.FavoritesInteractor
import ru.practicum.android.diploma.domain.LikeInteractor
import ru.practicum.android.diploma.domain.api.SearchVacanciesInteractor
import ru.practicum.android.diploma.domain.api.SharingInteractor
import ru.practicum.android.diploma.domain.api.VacancyInteractor
import ru.practicum.android.diploma.domain.converters.VacancyMapper
import ru.practicum.android.diploma.domain.impl.FavoritesInteractorImpl
import ru.practicum.android.diploma.domain.impl.LikeInteractorImpl
import ru.practicum.android.diploma.domain.impl.SearchVacanciesInteractorImpl
import ru.practicum.android.diploma.domain.impl.SharingInteractorImpl
import ru.practicum.android.diploma.domain.impl.VacancyInteractorImpl

val domainModule = module {
    single { VacancyMapper(get()) }

    single<SearchVacanciesInteractor> {
        SearchVacanciesInteractorImpl(get())
    }

    single<LikeInteractor> { LikeInteractorImpl(get()) }
    single<VacancyInteractor> { VacancyInteractorImpl(get()) }

    single<SharingInteractor> { SharingInteractorImpl(get()) }
    single<FavoritesInteractor> { FavoritesInteractorImpl(get()) }
}
