package ru.practicum.android.diploma.ui.filter.industry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.presentation.filter.industry.IndustryFilterState
import ru.practicum.android.diploma.presentation.filter.industry.IndustryFilterViewModel
import ru.practicum.android.diploma.ui.common.ButtonControl
import ru.practicum.android.diploma.ui.common.ButtonControlType
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
                        ContentState(
                            filterState = state,
                            selectedIndustryState = viewModel.selectedIndustry,
                            onSelectionChange = { industry ->
                                viewModel.selectIndustry(industry)
                            },
                            onBackClick = onBackClick
                        )
                    }

                    IndustryFilterState.NotFound -> {
                        PlaceholderState(
                            painterResource(id = R.drawable.placeholder_region_error),
                            stringResource(R.string.region_selection_error)
                        )
                    }

                    IndustryFilterState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = Dimens.padding16),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                Modifier.size(Dimens.progressIndicatorSize)
                            )
                        }
                    }

                    IndustryFilterState.NoInternet -> {
                        PlaceholderState(
                            painterResource(id = R.drawable.placeholder_no_internet),
                            stringResource(R.string.no_internet)
                        )
                    }

                    IndustryFilterState.ServerError -> {
                        PlaceholderState(
                            painterResource(id = R.drawable.placeholder_server_error),
                            stringResource(R.string.server_error)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContentState(
    filterState: IndustryFilterState.Content,
    selectedIndustryState: StateFlow<Industry?>,
    onSelectionChange: (industry: Industry?) -> Unit,
    onBackClick: () -> Unit
) {
    val selectedIndustry by selectedIndustryState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding8)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(Dimens.padding2)
        ) {
            items(filterState.industries) { industry ->
                val isSelected = selectedIndustry?.let { it.id == industry.id } ?: false
                TextWithRadioButton(
                    Modifier,
                    text = industry.name,
                    isSelected = isSelected,
                    onSelectionChange = {
                        if (isSelected) {
                            onSelectionChange(null)
                        } else {
                            onSelectionChange(industry)
                        }
                    }
                )
            }
        }

        if (selectedIndustry != null) {
            ButtonControl(
                Modifier,
                text = stringResource(R.string.select),
                onClick = {
                    onBackClick()
                },
                buttonControlType = ButtonControlType.Approve,
            )
        }
    }
}
