package ru.practicum.android.diploma.ui.filter.industry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.filter.industry.IndustryFilterState
import ru.practicum.android.diploma.presentation.filter.industry.IndustryFilterViewModel
import ru.practicum.android.diploma.ui.common.FilterTopAppBar
import ru.practicum.android.diploma.ui.common.PlaceholderState
import ru.practicum.android.diploma.ui.common.SearchTextField
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens

class IndustryFilterFragment : Fragment() {
    val industryFilterViewModel: IndustryFilterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            AndroidDiplomaTheme {
                IndustryFilterScreen(
                    viewModel = industryFilterViewModel,
                    onBackClick = { findNavController().navigateUp() }
                )
            }
        }
    }
}

@Composable
fun IndustryFilterScreen(viewModel: IndustryFilterViewModel, onBackClick: () -> Unit) {
    val searchQuery by viewModel.query.collectAsState()
    val filterState by viewModel.filterState.collectAsState()
    val selectedIndustryId by viewModel.selectedIndustryId.collectAsState()

    Scaffold(
        topBar = {
            FilterTopAppBar(
                title = stringResource(R.string.industry_selection),
                onBackClick = { onBackClick() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Dimens.padding16)
        ) {
            SearchTextField(
                searchQuery = searchQuery,
                placeholder = stringResource(R.string.enter_industry),
                onQueryChange = { text -> viewModel.onSearchTextDebounce(text) },
                onClearQuery = { viewModel.clearSearchQuery() }
            )
            Spacer(modifier = Modifier.height(Dimens.padding8))

            filterState?.let { state ->
                when (state) {
                    is IndustryFilterState.Content -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(Dimens.padding2)
                        ) {
                            items((filterState as IndustryFilterState.Content).industries) { industry ->
                                TextWithRadioButton(
                                    Modifier,
                                    text = industry.name,
                                    isSelected = selectedIndustryId == industry.id,
                                    onSelectionChange = {
                                        if (selectedIndustryId == industry.id) {
                                            viewModel.selectIndustry(null)
                                        } else {
                                            viewModel.selectIndustry(industry.id)
                                        }
                                    }
                                )
                            }
                        }
                    }

                    IndustryFilterState.Fail -> {
                        PlaceholderState(
                            painterResource(id = R.drawable.placeholder_region_error),
                            stringResource(R.string.region_selection_error)
                        )
                    }
                }
            }
        }
    }
}
