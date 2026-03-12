package ru.practicum.android.diploma.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.VacancyShort
import ru.practicum.android.diploma.presentation.favorites.FavoritesState
import ru.practicum.android.diploma.presentation.favorites.FavoritesViewModel
import ru.practicum.android.diploma.ui.common.PlaceholderState
import ru.practicum.android.diploma.ui.common.VacancyItem
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography
import ru.practicum.android.diploma.ui.vacancy.VacancyFragment
import ru.practicum.android.diploma.util.debounce

class FavoritesFragment : Fragment() {
    private val vacancyClickDebounce =
        debounce<String>(CLICK_DEBOUNCE_DELAY, lifecycleScope, true) { vacancyId ->
            findNavController().navigate(
                R.id.action_favoritesFragment_to_vacancyFragment,
                VacancyFragment.createArgs(vacancyId)
            )
        }
    private val favoritesViewModel: FavoritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            AndroidDiplomaTheme {
                FavoritesScreen(
                    modifier = Modifier,
                    favoritesViewModel,
                    navigateToVacancy = { vacancyId ->
                        vacancyClickDebounce(vacancyId)
                    }
                )
            }
        }
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 300L
    }
}

@Composable
fun FavoritesScreen(
    modifier: Modifier,
    favoritesViewModel: FavoritesViewModel,
    navigateToVacancy: (vacancyId: String) -> Unit
) {
    val header = stringResource(R.string.favorites)
    val state by favoritesViewModel.stateFlow().collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Dimens.padding16, end = Dimens.padding16)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .height(Dimens.headerHeight)
                .padding(top = Dimens.padding20),
            text = header,
            textAlign = TextAlign.Left,
            color = LocalAndroidDiplomaScheme.current.vacancy.title,
            style = LocalAndroidDiplomaTypography.current.medium22
        )

        state?.let {
            when (it) {
                is FavoritesState.Content -> {
                    if (it.vacancies.isEmpty()) {
                        NoVacanciesState()
                    } else {
                        WithVacanciesState(it.vacancies, navigateToVacancy)
                    }
                }

                FavoritesState.Fail -> FailState()
            }
        }
    }
}

@Composable
fun WithVacanciesState(
    vacancies: List<VacancyShort>,
    navigateToVacancy: (vacancyId: String) -> Unit
) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(top = Dimens.padding8),
    ) {
        items(vacancies) { vacancy ->
            VacancyItem(
                vacancy,
                { navigateToVacancy(vacancy.id) },
            )
        }
    }
}

@Composable
fun NoVacanciesState() {
    PlaceholderState(
        painterResource(id = R.drawable.placeholder_empty_favorites),
        stringResource(R.string.empty_list)
    )
}

@Composable
fun FailState() {
    PlaceholderState(
        painterResource(id = R.drawable.placeholder_empty_search),
        stringResource(R.string.cant_get_vacancies)
    )
}
