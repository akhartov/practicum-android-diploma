package ru.practicum.android.diploma.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.ButtonColors
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun ButtonControl(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    buttonControlType: ButtonControlType
) {
    val buttonColors = buttonControlType.getConfig(LocalAndroidDiplomaScheme.current.buttonColors)

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.buttonHeight),
        onClick = { onClick() },
        content = {
            Text(
                text,
                style = LocalAndroidDiplomaTypography.current.medium16,
            )
        },
        shape = RoundedCornerShape(Dimens.cornerRadius12),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColors.backgroundColor,
            contentColor = buttonColors.textColor,
        ),
    )

}

sealed class ButtonControlType {
    abstract fun getConfig(customButtonColors: ButtonColors): Config

    data object Approve : ButtonControlType() {
        override fun getConfig(customButtonColors: ButtonColors): Config {
            return Config(
                backgroundColor = customButtonColors.approve.background,
                textColor = customButtonColors.approve.text,
            )
        }
    }

    data object Decline : ButtonControlType() {
        override fun getConfig(customButtonColors: ButtonColors): Config {
            return Config(
                backgroundColor = customButtonColors.decline.background,
                textColor = customButtonColors.decline.text,
            )
        }
    }

    data class Config(
        val backgroundColor: Color,
        val textColor: Color,
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewApproveButtonDay() {
    AndroidDiplomaTheme {
        ButtonControl(
            Modifier,
            text = "Approve",
            onClick = {},
            buttonControlType = ButtonControlType.Approve,
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewApproveButtonNight() {
    AndroidDiplomaTheme {
        ButtonControl(
            Modifier,
            text = "Approve",
            onClick = {},
            buttonControlType = ButtonControlType.Approve,
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewDeclineButtonDay() {
    AndroidDiplomaTheme {
        ButtonControl(
            Modifier,
            text = "Decline",
            onClick = {},
            buttonControlType = ButtonControlType.Decline,
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewDeclineButtonNight() {
    AndroidDiplomaTheme {
        ButtonControl(
            Modifier,
            text = "Decline",
            onClick = {},
            buttonControlType = ButtonControlType.Decline,
        )
    }
}
