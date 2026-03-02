package ru.practicum.android.diploma.ui.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun SearchTopAppBar(
    title: String,
    onFilterIconClick: () -> Unit
) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        contentColor = LocalAndroidDiplomaScheme.current.topBar.text
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.searchBarHeight)
                .padding(end = Dimens.padding8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(Dimens.padding16))
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
            IconButton(onClick = { onFilterIconClick.invoke() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = null,
                    tint = LocalAndroidDiplomaScheme.current.topBar.icon
                )
            }
        }
    }
}
