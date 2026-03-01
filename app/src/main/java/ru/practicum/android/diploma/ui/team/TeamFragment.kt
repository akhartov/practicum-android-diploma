package ru.practicum.android.diploma.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens

class TeamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            AndroidDiplomaTheme {
                TeamScreen()
            }
        }
    }
}

@Composable
fun TeamScreen() {
    Column {
        TeamScreenTitle()
        Spacer(Modifier.height(Dimens.spacer8))
        TeamTitle()
        Spacer(Modifier.height(Dimens.spacer24))
        TeamMember(stringResource(R.string.dev1))
        TeamMember(stringResource(R.string.dev2))
        TeamMember(stringResource(R.string.dev3))
        TeamMember(stringResource(R.string.dev4))
    }
}
