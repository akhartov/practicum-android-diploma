package ru.practicum.android.diploma.ui.team

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun TeamMember(memberName: String) {
    Column {
        Text(
            text = memberName,
            style = LocalAndroidDiplomaTypography.current.medium16.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = Dimens.padding16)
        )
        Spacer(Modifier.height(16.dp))
    }
}
