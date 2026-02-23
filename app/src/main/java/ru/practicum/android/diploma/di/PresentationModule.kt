package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.presentation.favorites.FavoritesViewModel
import ru.practicum.android.diploma.presentation.filter.CountryFilterViewModel
import ru.practicum.android.diploma.presentation.filter.FilterSettingsViewModel
import ru.practicum.android.diploma.presentation.filter.IndustryFilterViewModel
import ru.practicum.android.diploma.presentation.filter.RegionFilterViewModel
import ru.practicum.android.diploma.presentation.filter.WorkplaceFilterViewModel
import ru.practicum.android.diploma.presentation.search.SearchViewModel
import ru.practicum.android.diploma.presentation.team.TeamViewModel
import ru.practicum.android.diploma.presentation.vacancy.VacancyViewModel

val presentationModule = module {

    viewModel {
        SearchViewModel()
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
        VacancyViewModel()
    }

    viewModel {
        FavoritesViewModel()
    }

    viewModel {
        TeamViewModel()
    }
}
