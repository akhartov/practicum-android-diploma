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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.filter.settings.FilterSettingsState
import ru.practicum.android.diploma.presentation.filter.settings.FilterSettingsViewModel
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
    val filterViewModel: FilterSettingsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            AndroidDiplomaTheme {
                FilterSettingsScreen(
                    saveFilter = { filterViewModel.saveFilter() },
                    resetFilter = { filterViewModel.resetFilter() },
                    navigateToWorkplaceFilter = {
                        findNavController().navigate(R.id.action_filterSettingsFragment_to_workplaceFilterFragment)
                    },
                    navigateToIndustryFilter = {
                        findNavController().navigate(R.id.action_filterSettingsFragment_to_industryFilterFragment)
                    },
                    filters = filterViewModel.filters,
                    changeWithSalaryOnly = { filterViewModel.changeWithSalaryOnly() },
                )
            }
        }
    }
}

@Composable
fun FilterSettingsScreen(
    navigateToWorkplaceFilter: () -> Unit,
    navigateToIndustryFilter: () -> Unit,
    saveFilter: () -> Unit,
    resetFilter: () -> Unit,
    filters: StateFlow<FilterSettingsState>,
    changeWithSalaryOnly: () -> Unit,
) {
    val filtersState by filters.collectAsState()
    var salaryInputted by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            FilterTopAppBar(
                title = stringResource(R.string.filter_settings),
                onBackClick = { }
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
                FilterSelectionControl(
                    Modifier,
                    onClick = navigateToWorkplaceFilter,
                    filterSectionControlType = FilterSectionControlType.Absent,
                    text = stringResource(R.string.workplace),
                )
                FilterSelectionControl(
                    Modifier,
                    onClick = navigateToIndustryFilter,
                    filterSectionControlType = FilterSectionControlType.Absent,
                    text = stringResource(R.string.industry),
                )
                Spacer(modifier = Modifier.height(Dimens.padding24))
                SalaryTextField(
                    salaryText = salaryInputted,
                    onTextChange = { newText ->
                        salaryInputted = newText
                    }
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

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewScreenDay() {
    val stateFlow = MutableStateFlow(FilterSettingsState())
    AndroidDiplomaTheme {
        FilterSettingsScreen(
            navigateToWorkplaceFilter = {},
            navigateToIndustryFilter = {},
            saveFilter = {},
            resetFilter = {},
            filters = stateFlow.asStateFlow(),
            changeWithSalaryOnly = {},
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewScreenNight() {
    val stateFlow = MutableStateFlow(FilterSettingsState())
    AndroidDiplomaTheme {
        FilterSettingsScreen(
            navigateToWorkplaceFilter = {},
            navigateToIndustryFilter = {},
            saveFilter = {},
            resetFilter = {},
            filters = stateFlow.asStateFlow(),
            changeWithSalaryOnly = {},
        )
    }
}
