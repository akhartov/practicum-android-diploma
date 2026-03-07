package ru.practicum.android.diploma.ui.filter.industry

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography
import ru.practicum.android.diploma.ui.theme.RadioButtonColors

@Composable
fun TextWithRadioButton(
    modifier: Modifier,
    text: String,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit,
    radioButtonItemType: RadioButtonItemType = RadioButtonItemType.Primary
) {
    val colors = radioButtonItemType.getConfig(LocalAndroidDiplomaScheme.current.radioButton)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.itemHeight),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                style = LocalAndroidDiplomaTypography.current.regular16.copy(
                    color = colors.textColor
                ),
            )
            RadioButton(
                selected = isSelected,
                onClick = { onSelectionChange(!isSelected) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = colors.tintColor,
                    unselectedColor = colors.tintColor
                )
            )
        }
    }
}

sealed class RadioButtonItemType {
    abstract fun getConfig(customRadioButtonColors: RadioButtonColors): Config

    data object Primary : RadioButtonItemType() {
        override fun getConfig(customRadioButtonColors: RadioButtonColors): Config {
            return Config(
                textColor = customRadioButtonColors.text,
                tintColor = customRadioButtonColors.tint
            )
        }
    }

    data class Config(
        val textColor: Color,
        val tintColor: Color,
    )
}

private const val PREVIEW_TEXT = "Автокомпоненты, запчасти, шины (продвижение, оптовая торговля)"
private const val PREVIEW_TEXT2 = "Авиационная, вертолетная и аэрокосмическая промышленность"

@Preview(
    device = "spec:width=1080px,height=1420px,dpi=420",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewCheckboxDay() {
    AndroidDiplomaTheme {
        Column {
            TextWithRadioButton(
                modifier = Modifier,
                text = PREVIEW_TEXT,
                isSelected = true,
                onSelectionChange = {},
                radioButtonItemType = RadioButtonItemType.Primary
            )
            TextWithRadioButton(
                modifier = Modifier,
                text = PREVIEW_TEXT2,
                isSelected = false,
                onSelectionChange = {},
                radioButtonItemType = RadioButtonItemType.Primary
            )
        }
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewCheckboxNight() {
    AndroidDiplomaTheme {
        Column {
            Column {
                TextWithRadioButton(
                    modifier = Modifier,
                    text = PREVIEW_TEXT,
                    isSelected = true,
                    onSelectionChange = {},
                    radioButtonItemType = RadioButtonItemType.Primary
                )

                TextWithRadioButton(
                    modifier = Modifier,
                    text = PREVIEW_TEXT2,
                    isSelected = false,
                    onSelectionChange = {},
                    radioButtonItemType = RadioButtonItemType.Primary
                )
            }
        }
    }
}
