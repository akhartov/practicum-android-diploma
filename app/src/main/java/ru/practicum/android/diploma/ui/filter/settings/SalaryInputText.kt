package ru.practicum.android.diploma.ui.filter.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun SalaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = value,
        onValueChange = { newText ->
            if (newText.isEmpty() || newText.all(Char::isDigit)) {
                onValueChange(newText)
            }
        },
        singleLine = true,

        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),

        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),

        cursorBrush = SolidColor(LocalAndroidDiplomaScheme.current.salaryInput.cursor),
        textStyle = LocalAndroidDiplomaTypography.current.regular16.copy(
            color = LocalAndroidDiplomaScheme.current.salaryInput.text
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.salaryBarHeight)
            .onFocusChanged { isFocused = it.isFocused },
        decorationBox = { innerTextField ->
            SalaryTextDecoration(
                modifier = modifier,
                value = value,
                isFocused = isFocused,
                onClear = onClear,
                innerTextField = innerTextField
            )
        }
    )
}

@Composable
private fun SalaryTextDecoration(
    modifier: Modifier,
    value: String,
    isFocused: Boolean,
    onClear: () -> Unit,
    innerTextField: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = LocalAndroidDiplomaScheme.current.salaryInput.background,
                shape = RoundedCornerShape(Dimens.cornerRadius12)
            )
            .padding(horizontal = Dimens.padding16, vertical = Dimens.padding8)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SalaryTextContent(
                value = value,
                isFocused = isFocused,
                innerTextField = innerTextField,
                modifier = Modifier.weight(1f)
            )

            if (isFocused && value.isNotEmpty()) {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    modifier = Modifier
                        .size(Dimens.filterIconSize)
                        .clickable(onClick = onClear),
                    tint = LocalAndroidDiplomaScheme.current.salaryInput.clearIcon
                )
            }
        }
    }
}

@Composable
private fun SalaryTextContent(
    value: String,
    isFocused: Boolean,
    innerTextField: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.expected_salary),
            style = LocalAndroidDiplomaTypography.current.regular12,
            color = if (isFocused) {
                LocalAndroidDiplomaScheme.current.salaryInput.focusedLabel
            } else {
                if (value.isEmpty()) {
                    LocalAndroidDiplomaScheme.current.salaryInput.unfocusedLabel
                } else {
                    LocalAndroidDiplomaScheme.current.salaryInput.unfocusedNotEmptyLabel
                }
            }
        )

        Box {
            if (value.isEmpty()) {
                Text(
                    text = stringResource(R.string.enter_amount),
                    style = LocalAndroidDiplomaTypography.current.regular16,
                    color = LocalAndroidDiplomaScheme.current.salaryInput.placeHolder
                )
            }
            innerTextField()
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewEmptyFieldNight() {
    var text by remember { mutableStateOf("") }
    AndroidDiplomaTheme {
        SalaryTextField(
            Modifier,
            text,
            {},
            {},
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewApproveButtonNight() {
    var text by remember { mutableStateOf("1234") }
    AndroidDiplomaTheme {
        SalaryTextField(
            Modifier,
            text,
            {},
            {},
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun PreviewEmptyFieldDay() {
    var text by remember { mutableStateOf("") }
    AndroidDiplomaTheme {
        SalaryTextField(
            Modifier,
            text,
            {},
            {},
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun PreviewApproveButtonDay() {
    var text by remember { mutableStateOf("1234") }
    AndroidDiplomaTheme {
        SalaryTextField(
            Modifier,
            text,
            {},
            {},
        )
    }
}
