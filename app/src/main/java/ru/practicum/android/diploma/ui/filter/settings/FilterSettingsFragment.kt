package ru.practicum.android.diploma.ui.filter.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.filter.settings.FilterSettingsState
import ru.practicum.android.diploma.presentation.filter.settings.FilterSettingsViewModel
import ru.practicum.android.diploma.presentation.filter.settings.isEmpty
import ru.practicum.android.diploma.ui.common.ButtonControl
import ru.practicum.android.diploma.ui.common.ButtonControlType
import ru.practicum.android.diploma.ui.common.CheckboxControl
import ru.practicum.android.diploma.ui.common.CheckboxControlType
import ru.practicum.android.diploma.ui.common.FilterSectionControlType
import ru.practicum.android.diploma.ui.common.FilterSelectionControl
import ru.practicum.android.diploma.ui.common.FilterTopAppBar
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens

class FilterSettingsFragment : Fragment() {
    val filterViewModel: FilterSettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            AndroidDiplomaTheme {
                FilterSettingsScreen(
                    saveFilter = {
                        filterViewModel.applyFilter()
                        findNavController().popBackStack()
                    },
                    resetFilter = {
                        filterViewModel.resetFilter()
                        findNavController().popBackStack()
                    },
                    navigateToWorkplaceFilter = { needReset ->
                        if (needReset) {
                            filterViewModel.resetWorkplace()
                        } else {
                            findNavController().navigate(R.id.action_filterSettingsFragment_to_workplaceFilterFragment)
                        }
                    },
                    navigateToIndustryFilter = { needReset ->
                        if (needReset) {
                            filterViewModel.resetIndustry()
                        } else {
                            findNavController().navigate(R.id.action_filterSettingsFragment_to_industryFilterFragment)
                        }
                    },
                    onBackClick = { findNavController().popBackStack() },
                    filters = filterViewModel.filtersUiState,
                    changeWithSalaryOnly = { filterViewModel.changeWithSalaryOnly() },
                    setSalaryValue = { salary -> filterViewModel.changeSalary(salary) }
                )
            }
        }
    }
}

@Composable
fun FilterSettingsScreen(
    navigateToWorkplaceFilter: (needReset: Boolean) -> Unit,
    navigateToIndustryFilter: (needReset: Boolean) -> Unit,
    onBackClick: () -> Unit,
    saveFilter: () -> Unit,
    resetFilter: () -> Unit,
    filters: StateFlow<FilterSettingsState>,
    changeWithSalaryOnly: () -> Unit,
    setSalaryValue: (String?) -> Unit
) {
    val filtersState by filters.collectAsState()
    Scaffold(
        topBar = {
            FilterTopAppBar(
                title = stringResource(R.string.filter_settings),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Dimens.padding16),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(Modifier) {
                if (filtersState.workplace != null) {
                    FilterSelectionControl(
                        Modifier,
                        onClick = { navigateToWorkplaceFilter(true) },
                        filterSectionControlType = FilterSectionControlType.Presents,
                        title = stringResource(R.string.workplace),
                        text = filtersState.workplace
                    )
                } else {
                    FilterSelectionControl(
                        Modifier,
                        onClick = { navigateToWorkplaceFilter(false) },
                        filterSectionControlType = FilterSectionControlType.Absent,
                        text = stringResource(R.string.workplace),
                    )
                }

                if (filtersState.industry != null) {
                    FilterSelectionControl(
                        Modifier,
                        onClick = { navigateToIndustryFilter(true) },
                        filterSectionControlType = FilterSectionControlType.Presents,
                        title = stringResource(R.string.industry),
                        text = filtersState.industry
                    )
                } else {
                    FilterSelectionControl(
                        Modifier,
                        onClick = { navigateToIndustryFilter(false) },
                        filterSectionControlType = FilterSectionControlType.Absent,
                        text = stringResource(R.string.industry),
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.padding24))
                SalaryTextField(
                    modifier = Modifier,
                    filtersState.salary ?: "",
                    onValueChange = setSalaryValue,
                    onClear = { setSalaryValue(null) },
                )
                Spacer(modifier = Modifier.height(Dimens.padding24))
                // Insert amount control here
                CheckboxControl(
                    modifier = Modifier,
                    text = stringResource(R.string.dont_show_without_salary),
                    isChecked = filtersState.isIncludeSalary,
                    onCheckedChange = { changeWithSalaryOnly() },
                    checkboxControlType = CheckboxControlType.Filter,
                )
            }

            if (!filtersState.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(Dimens.padding8)
                ) {
                    ButtonControl(
                        Modifier,
                        text = stringResource(R.string.apply),
                        onClick = { saveFilter() },
                        buttonControlType = ButtonControlType.Approve,
                    )
                    ButtonControl(
                        Modifier,
                        text = stringResource(R.string.reset),
                        onClick = { resetFilter() },
                        buttonControlType = ButtonControlType.Decline,
                    )
                }
            }
        }
    }
}

private const val PREVIEW_INDUSTRY = "Search for Extraterrestrial Intelligence"
private const val PREVIEW_WORKPLACE = "Russia, Alaska"

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun PreviewScreenDay() {
    val stateFlow = MutableStateFlow(FilterSettingsState())
    AndroidDiplomaTheme {
        FilterSettingsScreen(
            navigateToWorkplaceFilter = {},
            navigateToIndustryFilter = {},
            onBackClick = {},
            saveFilter = {},
            resetFilter = {},
            filters = stateFlow.asStateFlow(),
            changeWithSalaryOnly = {},
            setSalaryValue = {}
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun PreviewScreenSelectedDay() {
    val stateFlow = MutableStateFlow(
        FilterSettingsState(
            workplace = PREVIEW_WORKPLACE,
            industry = PREVIEW_INDUSTRY,
            salary = "300000",
            isIncludeSalary = true,
        )
    )
    AndroidDiplomaTheme {
        FilterSettingsScreen(
            navigateToWorkplaceFilter = {},
            navigateToIndustryFilter = {},
            onBackClick = {},
            saveFilter = {},
            resetFilter = {},
            filters = stateFlow.asStateFlow(),
            changeWithSalaryOnly = {},
            setSalaryValue = {}
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun PreviewScreenNight() {
    val stateFlow = MutableStateFlow(FilterSettingsState())
    AndroidDiplomaTheme {
        FilterSettingsScreen(
            navigateToWorkplaceFilter = {},
            navigateToIndustryFilter = {},
            onBackClick = {},
            saveFilter = {},
            resetFilter = {},
            filters = stateFlow.asStateFlow(),
            changeWithSalaryOnly = {},
            setSalaryValue = {}
        )
    }
}
