package ru.practicum.android.diploma.ui.team

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun TeamMember(memberName: String) {
    Text(
        text = memberName,
        style = LocalAndroidDiplomaTypography.current.medium16.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier
            .height(Dimens.teamMemberHeight)
            .wrapContentWidth()
            .padding(
                start = Dimens.padding16,
                end = Dimens.padding16,
                bottom = Dimens.padding16
            )
    )
}
