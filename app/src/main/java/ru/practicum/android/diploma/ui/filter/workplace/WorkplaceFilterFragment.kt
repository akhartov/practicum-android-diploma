package ru.practicum.android.diploma.ui.filter.workplace

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

class WorkplaceFilterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            WorkplaceFilterScreen(
                navigateToCountryFilter = {
                    findNavController().navigate(R.id.action_workplaceFilterFragment_to_countryFilterFragment)
                },
                navigateToRegionFilter = {
                    findNavController().navigate(R.id.action_workplaceFilterFragment_to_regionFilterFragment)
                }
            )
        }
    }
}

@Composable
fun WorkplaceFilterScreen(
    navigateToCountryFilter: () -> Unit,
    navigateToRegionFilter: () -> Unit
) {
    Column {
        Text(stringResource(R.string.workplace_selection))
        Button(
            onClick = navigateToCountryFilter,
            content = { Text(stringResource(R.string.country_selection)) },
        )
        Button(
            onClick = navigateToRegionFilter,
            content = { Text(stringResource(R.string.region_selection)) },
        )
    }
}
