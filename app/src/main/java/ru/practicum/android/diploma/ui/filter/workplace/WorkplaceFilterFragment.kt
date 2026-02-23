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
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.R

class WorkplaceFilterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
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
}

@Composable
fun WorkplaceFilterScreen(
    navigateToCountryFilter: () -> Unit,
    navigateToRegionFilter: () -> Unit
) {
    Column {
        Text("Выбор места работы")
        Button(
            onClick = navigateToCountryFilter,
            content = { Text("Выбор страны") },
        )
        Button(
            onClick = navigateToRegionFilter,
            content = { Text("Выбор региона") },
        )
    }
}
