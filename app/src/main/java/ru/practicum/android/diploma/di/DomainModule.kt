package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.data.converters.IndustryMapper
import ru.practicum.android.diploma.data.impl.FavoritesRepositoryImpl
import ru.practicum.android.diploma.data.repository.FavoritesRepository
import ru.practicum.android.diploma.domain.GetVacanciesFlowUseCase
import ru.practicum.android.diploma.domain.LikeInteractor
import ru.practicum.android.diploma.domain.api.AreaInteractor
import ru.practicum.android.diploma.domain.api.ChangeCountryUseCase
import ru.practicum.android.diploma.domain.api.ChangeFilterSettingsInteractor
import ru.practicum.android.diploma.domain.api.ChangeIndustryInteractor
import ru.practicum.android.diploma.domain.api.ChangeRegionUseCase
import ru.practicum.android.diploma.domain.api.ChangeWorkplaceInteractor
import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.api.FiltersWatchingUseCase
import ru.practicum.android.diploma.domain.api.IndustryInteractor
import ru.practicum.android.diploma.domain.api.SearchVacanciesInteractor
import ru.practicum.android.diploma.domain.api.SharingInteractor
import ru.practicum.android.diploma.domain.api.VacancyInteractor
import ru.practicum.android.diploma.domain.impl.AreaInteractorImpl
import ru.practicum.android.diploma.domain.impl.FilterInteractorImpl
import ru.practicum.android.diploma.domain.impl.IndustryInteractorImpl
import ru.practicum.android.diploma.domain.impl.LikeInteractorImpl
import ru.practicum.android.diploma.domain.impl.SearchVacanciesInteractorImpl
import ru.practicum.android.diploma.domain.impl.SharingInteractorImpl
import ru.practicum.android.diploma.domain.impl.VacancyInteractorImpl

val domainModule = module {
    single { IndustryMapper() }

    single<SearchVacanciesInteractor> {
        SearchVacanciesInteractorImpl(get())
    }
    single<IndustryInteractor> {
        IndustryInteractorImpl(get())
    }
    single<FavoritesRepository> { FavoritesRepositoryImpl(get(), get()) }
    single<LikeInteractor> { LikeInteractorImpl(get()) }
    single<VacancyInteractor> { VacancyInteractorImpl(get()) }

    single<SharingInteractor> { SharingInteractorImpl(get()) }
    single { GetVacanciesFlowUseCase(get()) }
    single<AreaInteractor> { AreaInteractorImpl(get()) }

    single<FilterInteractor> { FilterInteractorImpl(get(), get()) }
    single { ChangeFilterSettingsInteractor(get()) }
    single { ChangeIndustryInteractor(get()) }

    single { ChangeRegionUseCase(get()) }
    single { ChangeCountryUseCase(get()) }
    single { ChangeWorkplaceInteractor(get()) }
    single { FiltersWatchingUseCase(get()) }
}
