package ru.practicum.android.diploma.ui.search

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.common.SearchTextField
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens

class SearchFragment : Fragment() {

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
        var searchQuery by remember { mutableStateOf("") }

        SearchScreen(
            searchQuery,
            onQueryChange = { newQuery ->
                searchQuery = newQuery
            },
            onFilterIconClick = {
                findNavController().navigate(R.id.action_searchFragment_to_filterSettingsFragment)
            },
            onVacancyItemClick = {
                findNavController().navigate(R.id.action_searchFragment_to_vacancyFragment)
            }
        )
    }
}

@Composable
fun SearchScreen(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onFilterIconClick: () -> Unit,
    onVacancyItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding16)
    ) {
        Text(stringResource(R.string.search_vacancies))
        Button(
            onClick = onFilterIconClick,
            content = { Text(stringResource(R.string.filter_settings)) },
        )
        Button(
            onClick = onVacancyItemClick,
            content = { Text(stringResource(R.string.vacancy)) },
        )
        SearchTextField(
            searchQuery = searchQuery,
            placeholder = stringResource(R.string.enter_search_query),
            onQueryChange = onQueryChange,
        )
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenEmptyPreview() {
    AndroidDiplomaTheme {
        SearchScreen(
            searchQuery = "",
            onQueryChange = {},
            onFilterIconClick = {},
            onVacancyItemClick = {},
        )
    }
}
