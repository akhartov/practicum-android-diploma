package ru.practicum.android.diploma.ui.filter.country

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun FilterSelectionControl(
    onClick: () -> Unit,
    text: String
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(Dimens.itemHeight)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = text,
            style = LocalAndroidDiplomaTypography.current.regular16,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(
                    start = Dimens.padding16,
                )
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(end = Dimens.padding16)
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewCountryDay() {
    AndroidDiplomaTheme {
        FilterSelectionControl(
            { },
            "Россия"
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewCountryNight() {
    AndroidDiplomaTheme {
        FilterSelectionControl(
            { },
            "Россия"
        )
    }
}
