package ru.practicum.android.diploma.ui.filter.industry

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography
import ru.practicum.android.diploma.ui.theme.RadioButtonColors

@Composable
fun TextWithRadioButton(
    text: String,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit,
    radioButtonItemType: RadioButtonItemType = RadioButtonItemType.Primary
) {
    val colors = radioButtonItemType.getConfig(LocalAndroidDiplomaScheme.current.radioButton)

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
