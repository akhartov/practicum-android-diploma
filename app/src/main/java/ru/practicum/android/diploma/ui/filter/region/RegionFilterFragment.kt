package ru.practicum.android.diploma.ui.filter.region

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.presentation.filter.region.RegionFilterViewModel
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme

class RegionFilterFragment : Fragment() {
    private val viewModel: RegionFilterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            AndroidDiplomaTheme {
                RegionFilterScreen(
                    onBackClick = { findNavController().popBackStack() },
                    regionFilter = viewModel.regionFilter,
                    onRegionClick = { region ->
                        viewModel.changeRegion(region)
                        findNavController().popBackStack()
                    },
                    viewModel.query,
                    onQueryChange = { text -> viewModel.onSearchTextDebounce(text) },
                    onClearQuery = { viewModel.clearSearchQuery() }
                )
            }
        }
    }
}

