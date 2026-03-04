package ru.practicum.android.diploma.ui.team

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun TeamTitle() {
    Text(
        text = stringResource(R.string.developers),
        style = LocalAndroidDiplomaTypography.current.bold32.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier
            .height(Dimens.teamTitleHeight)
            .wrapContentWidth()
            .padding(
                vertical = Dimens.padding8
            )
    )
}
