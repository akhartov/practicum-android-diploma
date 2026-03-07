package ru.practicum.android.diploma.ui.filter.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.filter.country.CountryFilterState
import ru.practicum.android.diploma.presentation.filter.country.CountryFilterViewModel
import ru.practicum.android.diploma.ui.common.FilterTopAppBar
import ru.practicum.android.diploma.ui.common.PlaceholderState
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme

class CountryFilterFragment : Fragment() {
    val countryFilterViewModel: CountryFilterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            AndroidDiplomaTheme {
                CountryFilterScreen(
                    viewModel = countryFilterViewModel,
                    onBackClick = { }
                )
            }
        }
    }
}

@Composable
fun CountryFilterScreen(
    viewModel: CountryFilterViewModel,
    onBackClick: () -> Unit
) {
    val countryState by viewModel.countryFilterState.collectAsState()
    Scaffold(
        topBar = {
            FilterTopAppBar(
                title = stringResource(R.string.country_selection),
                onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            countryState?.let { state ->
                when (state) {
                    is CountryFilterState.Content -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.countries) { country ->
                                CountryItem(
                                    onClick = onBackClick,
                                    text = country.name
                                )
                            }
                        }
                    }

                    is CountryFilterState.Fail -> {
                        PlaceholderState(
                            painter = painterResource(id = R.drawable.placeholder_region_error),
                            text = stringResource(id = R.string.region_selection_error)
                        )
                    }

                    is CountryFilterState.Loading -> { }
                }
            }
        }
    }

}
