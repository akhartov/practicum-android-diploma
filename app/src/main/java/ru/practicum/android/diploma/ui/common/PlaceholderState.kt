package ru.practicum.android.diploma.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun PlaceholderState(
    painter: Painter,
    text: String
) {
    val textState by remember { mutableStateOf(text) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painter,
                contentDescription = null,
                alignment = Alignment.Center
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Dimens.padding16),
                text = textState,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
        }
    }
}
