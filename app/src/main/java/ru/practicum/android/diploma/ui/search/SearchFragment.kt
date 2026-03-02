package ru.practicum.android.diploma.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.VacancyShort
import ru.practicum.android.diploma.presentation.search.SearchViewModel
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.util.debounce

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()

    private val vacancyClickDebounce = debounce<String>(
        CLICK_VACANCY_DEBOUNCE_DELAY,
        lifecycleScope,
        true
    ) { vacancyId ->
        findNavController().navigate(
            R.id.action_searchFragment_to_vacancyFragment,
//            TODO: VacancyFragment.createArgs(vacancyId)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            AndroidDiplomaTheme {
                SearchScreenContent()
            }
        }
    }

    @Composable
    fun SearchScreenContent() {
        val state by viewModel.state.collectAsState()
//        val state = VacanciesState.Empty
//        val state = VacanciesState.Loading
//        val state = VacanciesState.NoInternet
//        val state = VacanciesState.NotFound
//        val state = VacanciesState.ServerError
//        val state = VacanciesState.Content(
//            VacancyShortResponse(
//                found = 35,
//                pages = 2,
//                1,
//                items = vacancies
//            )
//        )

        var searchQuery by remember { mutableStateOf("") }

        SearchScreen(
            state = state,
            searchQuery = searchQuery,
            onQueryChange = { newQuery ->
                searchQuery = newQuery
                viewModel.onSearchTextDebounce(newQuery)
            },
            onFilterIconClick = {
                findNavController().navigate(R.id.action_searchFragment_to_filterSettingsFragment)
            },
            onVacancyItemClick = { vacancyId ->
                vacancyClickDebounce(vacancyId)
            },
            onLoadNextPage = {
                viewModel.onLoadNextPage()
            },
            isNextPageLoading = viewModel.isNextPageLoading
        )
    }

    companion object {
        private const val CLICK_VACANCY_DEBOUNCE_DELAY = 300L
    }
}

val vacancies: List<VacancyShort> = listOf(
    VacancyShort(
        id = "1",
        vacancyTitle = "Андроид-разработчик, Москва",
        employerName = "Еда",
        employerLogoUrl = null,
        salaryString = "от 100 000 ₽"
    ),
    VacancyShort(
        id = "2",
        vacancyTitle = "Разработчик на С++ в команду внутренних сервисов, Москва",
        employerName = "Google",
        employerLogoUrl = null,
        salaryString = "от 40 000 до 80 000 ₽"
    ),
    VacancyShort(
        id = "3",
        vacancyTitle = "Разработчик платформы данных, Санкт-Петербург",
        employerName = "Алиса",
        employerLogoUrl = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
        salaryString = "Зарплата не указана"
    ),
    VacancyShort(
        id = "4",
        vacancyTitle = "Разработчик бэкенда, Москва",
        employerName = "Авто.ру",
        employerLogoUrl = null,
        salaryString = "от 1500 $"
    ),
    VacancyShort(
        id = "5",
        vacancyTitle = "Разработчик платформы данных, Санкт-Петербург",
        employerName = "Алиса",
        employerLogoUrl = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
        salaryString = "Зарплата не указана"
    ),
    VacancyShort(
        id = "6",
        vacancyTitle = "Разработчик бэкенда, Москва",
        employerName = "Авто.ру",
        employerLogoUrl = null,
        salaryString = "от 1500 $"
    )
)
