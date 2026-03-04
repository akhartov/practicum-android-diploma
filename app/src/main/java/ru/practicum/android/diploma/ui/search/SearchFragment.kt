package ru.practicum.android.diploma.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.model.ToastState
import ru.practicum.android.diploma.presentation.search.SearchViewModel
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.vacancy.VacancyFragment
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
            VacancyFragment.createArgs(vacancyId)
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
                SearchScreenContent(LocalContext.current)
            }
        }
    }

    @Composable
    fun SearchScreenContent(context: Context) {
        val state by viewModel.state.collectAsState()

        var searchQuery by remember { mutableStateOf("") }

        val toastEvent by viewModel.toastState.collectAsState()

        LaunchedEffect(toastEvent) {
            val toastText = when (toastEvent) {
                ToastState.NoInternet -> context.resources.getString(R.string.check_connection)
                ToastState.ServerError -> context.resources.getString(R.string.server_error)
                ToastState.NoProblem -> ""
            }

            if (toastText.isNotBlank()) {
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
            }

            viewModel.clearToast()
        }

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
            isSearchInProgress = viewModel.isSearchInProgress,
        )
    }

    companion object {
        private const val CLICK_VACANCY_DEBOUNCE_DELAY = 300L
    }
}
