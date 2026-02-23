package ru.practicum.android.diploma.ui.favorites

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

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                FavoritesScreen(
                    navigateToVacancy = {
                        findNavController().navigate(R.id.action_favoritesFragment_to_vacancyFragment)
                    }
                )
            }
        }
    }
}

@Composable
fun FavoritesScreen(
    navigateToVacancy: () -> Unit
) {
    Column {
        Text("Избранное")
        Button(
            onClick = navigateToVacancy,
            content = { Text("Вакансия") },
        )
    }
}
