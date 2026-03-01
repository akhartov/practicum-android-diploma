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
fun TeamScreenTitle() {
    Text(
        text = stringResource(R.string.team),
        style = LocalAndroidDiplomaTypography.current.medium22.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier
            .height(Dimens.teamTeamScreenTitltHeight)
            .wrapContentWidth()
            .padding(vertical = Dimens.padding20)
            .padding(start = Dimens.padding16)
            .padding(end = Dimens.padding8)
    )
}
