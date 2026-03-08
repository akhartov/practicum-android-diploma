package ru.practicum.android.diploma.ui.filter.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Blue
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun SalaryTextField(
    setSalaryValue: (String?) -> Unit,
    salaryText: String
) {
    var isFocused by remember { mutableStateOf(false) }
    var textState by remember { mutableStateOf(salaryText) }

    LaunchedEffect(salaryText) {
        if (textState != salaryText) {
            textState = salaryText
        }
    }

    BasicTextField(
        value = textState,
        onValueChange = { newText ->
            if (newText.isDigitsOnly()) {
                textState = newText
                if (newText.isEmpty()) {
                    setSalaryValue(null)
                } else {
                    setSalaryValue(newText)
                }
            }
        },
        singleLine = true,
        cursorBrush = SolidColor(Blue),
        textStyle = LocalAndroidDiplomaTypography.current.regular16,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),

        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .height(52.dp)
                    .clip(RoundedCornerShape(Dimens.cornerRadius12))
                    .background(LocalAndroidDiplomaScheme.current.salaryInput.background),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = Dimens.padding16)
                        .onFocusEvent { focusState ->
                            isFocused = focusState.hasFocus
                        }

                ) {
                    // Лейбл сверху внутри поля
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = Dimens.padding8),
                        text = stringResource(R.string.expected_salary),
                        style = LocalAndroidDiplomaTypography.current.regular12,
                        color = when {
                            isFocused || salaryText.isNotEmpty() -> Blue
                            else -> LocalAndroidDiplomaScheme.current.salaryInput.placeHolder
                        }
                    )

                    // Контейнер для текста
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = Dimens.padding16)
                    ) {
                        PlaceholderText(salaryText.isEmpty())

                        innerTextField()
                    }
                }
                ClearButton(
                    salaryText.isNotEmpty(),
                    onClearClick = {
                        textState = ""
                        setSalaryValue(null)
                    }
                )
            }
        }
    )
}

@Composable
private fun PlaceholderText(
    show: Boolean
) {
    if (show) {
        Text(
            text = stringResource(R.string.enter_amount),
            style = LocalAndroidDiplomaTypography.current.regular16,
            color = LocalAndroidDiplomaScheme.current.salaryInput.placeHolder
        )
    }
}

@Composable
private fun ClearButton(
    show: Boolean,
    onClearClick: () -> Unit
) {
    if (show) {
        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
            tint = LocalAndroidDiplomaScheme.current.salaryInput.clearIcon,
            modifier = Modifier
                .wrapContentSize()
                .padding(end = Dimens.padding16)
                .clickable { onClearClick() }
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewApproveButtonDay() {
    var text by remember { mutableStateOf("") }
    AndroidDiplomaTheme {
        SalaryTextField(
            {},
            salaryText = text
        )
    }
}
