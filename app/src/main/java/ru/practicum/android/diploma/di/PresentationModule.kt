package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.presentation.favorites.FavoritesViewModel
import ru.practicum.android.diploma.presentation.filter.CountryFilterViewModel
import ru.practicum.android.diploma.presentation.filter.FilterSettingsViewModel
import ru.practicum.android.diploma.presentation.filter.industry.IndustryFilterViewModel
import ru.practicum.android.diploma.presentation.filter.RegionFilterViewModel
import ru.practicum.android.diploma.presentation.filter.WorkplaceFilterViewModel
import ru.practicum.android.diploma.presentation.search.SearchViewModel
import ru.practicum.android.diploma.presentation.team.TeamViewModel
import ru.practicum.android.diploma.presentation.vacancy.VacancyViewModel
import ru.practicum.android.diploma.util.CurrencyMapper
import ru.practicum.android.diploma.util.SalaryMapper

val presentationModule = module {

    viewModel {
        SearchViewModel(get())
    }

    viewModel {
        FilterSettingsViewModel()
    }

    viewModel {
        WorkplaceFilterViewModel()
    }

    viewModel {
        CountryFilterViewModel()
    }

    viewModel {
        RegionFilterViewModel()
    }

    viewModel {
        IndustryFilterViewModel()
    }

    viewModel {
        VacancyViewModel(get(), get(), get())
    }

    viewModel {
        FavoritesViewModel(get())
    }

    viewModel {
        TeamViewModel()
    }

    single { CurrencyMapper() }

    single { SalaryMapper(androidContext(), get()) }
}
