package ru.practicum.android.diploma.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.CheckboxColors
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun CheckboxControl(
    modifier: Modifier,
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    checkboxControlType: CheckboxControlType,
) {
    val checkboxColors = checkboxControlType.getConfig(LocalAndroidDiplomaScheme.current.checkboxColors)

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
                    color = checkboxColors.textColor
                )
            )
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = checkboxColors.tintColor,
                    uncheckedColor = checkboxColors.tintColor,
                    checkmarkColor = checkboxColors.checkmarkColor
                ),
            )
        }
    }
}

sealed class CheckboxControlType {
    abstract fun getConfig(customColors: CheckboxColors): Config

    data object Filter : CheckboxControlType() {
        override fun getConfig(customColors: CheckboxColors): Config {
            return Config(
                textColor = customColors.filter.text,
                tintColor = customColors.filter.tint,
                checkmarkColor = customColors.filter.checkmark,
            )
        }
    }

    data class Config(
        val textColor: Color,
        val tintColor: Color,
        val checkmarkColor: Color,
    )
}

private const val PREVIEW_CHECKBOX_TEXT = "Не показывать без зарплаты"

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewCheckboxDay() {
    AndroidDiplomaTheme {
        Column {
            CheckboxControl(Modifier, PREVIEW_CHECKBOX_TEXT, true, {}, CheckboxControlType.Filter)
            CheckboxControl(Modifier, PREVIEW_CHECKBOX_TEXT, false, {}, CheckboxControlType.Filter)
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
            CheckboxControl(Modifier, PREVIEW_CHECKBOX_TEXT, true, {}, CheckboxControlType.Filter)
            CheckboxControl(Modifier, PREVIEW_CHECKBOX_TEXT, false, {}, CheckboxControlType.Filter)
        }
    }
}
