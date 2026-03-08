package ru.practicum.android.diploma.ui.filter.workplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.presentation.filter.WorkplaceFilterViewModel
import ru.practicum.android.diploma.ui.common.ButtonControl
import ru.practicum.android.diploma.ui.common.ButtonControlType
import ru.practicum.android.diploma.ui.common.FilterSectionControlType
import ru.practicum.android.diploma.ui.common.FilterSelectionControl
import ru.practicum.android.diploma.ui.common.FilterTopAppBar
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens

class WorkplaceFilterFragment : Fragment() {
    val viewModel: WorkplaceFilterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            AndroidDiplomaTheme {
                WorkplaceFilterScreen(
                    country = viewModel.country,
                    area = viewModel.area,
                    navigateToCountryFilter = {
                        findNavController().navigate(R.id.action_workplaceFilterFragment_to_countryFilterFragment)
                    },
                    navigateToRegionFilter = {
                        findNavController().navigate(R.id.action_workplaceFilterFragment_to_regionFilterFragment)
                    },
                    onApply = {
                        viewModel.applyWorkplace()
                        findNavController().popBackStack()
                    },
                    onBackClick = { findNavController().popBackStack() }
                )
            }
        }
    }
}

@Composable
fun WorkplaceFilterScreen(
    country: StateFlow<Area?>,
    area: StateFlow<Area?>,
    navigateToCountryFilter: () -> Unit,
    navigateToRegionFilter: () -> Unit,
    onApply: () -> Unit,
    onBackClick: () -> Unit,
) {
    val countryState = country.collectAsState()
    val areaState = area.collectAsState()
    Scaffold(
        topBar = {
            FilterTopAppBar(
                title = stringResource(R.string.workplace_selection),
                onBackClick = { onBackClick() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = Dimens.padding16),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                if (countryState.value?.name != null) {
                    FilterSelectionControl(
                        Modifier,
                        onClick = { navigateToCountryFilter() },
                        filterSectionControlType = FilterSectionControlType.Presents,
                        title = stringResource(R.string.country_selection),
                        text = countryState.value?.name
                    )
                } else {
                    FilterSelectionControl(
                        Modifier,
                        onClick = { navigateToCountryFilter() },
                        filterSectionControlType = FilterSectionControlType.Absent,
                        text = stringResource(R.string.country_selection),
                    )
                }
                if (areaState.value?.name != null) {
                    FilterSelectionControl(
                        Modifier,
                        onClick = { navigateToRegionFilter() },
                        filterSectionControlType = FilterSectionControlType.Presents,
                        title = stringResource(R.string.region_selection),
                        text = areaState.value?.name
                    )
                } else {
                    FilterSelectionControl(
                        Modifier,
                        onClick = { navigateToRegionFilter() },
                        filterSectionControlType = FilterSectionControlType.Absent,
                        text = stringResource(R.string.region_selection),
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Dimens.padding8)
            ) {
                ButtonControl(
                    Modifier,
                    text = stringResource(R.string.select),
                    onClick = { onApply() },
                    buttonControlType = ButtonControlType.Approve,
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
    val area = MutableStateFlow<Area?>(null)
    val country = MutableStateFlow<Area?>(null)

    AndroidDiplomaTheme {
        WorkplaceFilterScreen(
            area,
            country,
            {},
            {},
            {},
            {},
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
    val area = MutableStateFlow<Area?>(null)
    val country = MutableStateFlow<Area?>(null)

    AndroidDiplomaTheme {
        WorkplaceFilterScreen(
            area,
            country,
            {},
            {},
            {},
            {},
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewScreenFilledNight() {
    val area = MutableStateFlow<Area?>(Area(1, "Area", null, listOf()))
    val country = MutableStateFlow<Area?>(Area(1, "Country", null, listOf()))

    AndroidDiplomaTheme {
        WorkplaceFilterScreen(
            area,
            country,
            {},
            {},
            {},
            {},
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewScreenFilledDay() {
    val area = MutableStateFlow<Area?>(Area(1, "Area", null, listOf()))
    val country = MutableStateFlow<Area?>(Area(1, "Country", null, listOf()))

    AndroidDiplomaTheme {
        WorkplaceFilterScreen(
            area,
            country,
            {},
            {},
            {},
            {},
        )
    }
}
