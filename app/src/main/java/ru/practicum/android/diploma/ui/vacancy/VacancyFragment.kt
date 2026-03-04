package ru.practicum.android.diploma.ui.vacancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.presentation.vacancy.VacancyViewModel
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme

class VacancyFragment : Fragment() {
    private val vacancyViewModel: VacancyViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        val id = arguments?.getString(SELECTED_VACANCY) ?: ""
        vacancyViewModel.getVacancy(id)
        setContent {
            AndroidDiplomaTheme {
                VacancyScreen(vacancyViewModel = koinViewModel(), onBack = { findNavController().navigateUp() })
            }
        }
    }

    companion object {
        private const val SELECTED_VACANCY = "selected_vacancy"

        fun createArgs(id: String): Bundle = bundleOf(SELECTED_VACANCY to id)
    }
}



