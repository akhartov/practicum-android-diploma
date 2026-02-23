package ru.practicum.android.diploma.ui.filter.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.R

class FilterSettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            FilterSettingsScreen(
                navigateToWorkplaceFilter = {
                    findNavController().navigate(R.id.action_filterSettingsFragment_to_workplaceFilterFragment)
                },
                navigateToIndustryFilter = {
                    findNavController().navigate(R.id.action_filterSettingsFragment_to_industryFilterFragment)
                }
            )
        }
    }
}

@Composable
fun FilterSettingsScreen(
    navigateToWorkplaceFilter: () -> Unit,
    navigateToIndustryFilter: () -> Unit
) {
    Column {
        Text(stringResource(R.string.filter_settings))
        Button(
            onClick = navigateToWorkplaceFilter,
            content = { Text(stringResource(R.string.workplace_selection)) },
        )
        Button(
            onClick = navigateToIndustryFilter,
            content = { Text(stringResource(R.string.industry_selection)) },
        )
    }
}
