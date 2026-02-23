package ru.practicum.android.diploma.ui.search

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

class SearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                SearchScreen(
                    navigateToFilterSettings = {
                        findNavController().navigate(R.id.action_searchFragment_to_filterSettingsFragment)
                    },
                    navigateToVacancy = {
                        findNavController().navigate(R.id.action_searchFragment_to_vacancyFragment)
                    }
                )
            }
        }
    }
}

@Composable
fun SearchScreen(
    navigateToFilterSettings: () -> Unit,
    navigateToVacancy: () -> Unit
) {
    Column {
        Text("Поиск вакансий")
        Button(
            onClick = navigateToFilterSettings,
            content = { Text("Настройки фильтрации") },
        )
        Button(
            onClick = navigateToVacancy,
            content = { Text("Вакансия") },
        )
    }
}
