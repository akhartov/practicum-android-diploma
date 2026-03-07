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
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Blue
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun SearchTextField(
    salaryText: String, onTextChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    BasicTextField(
        value = salaryText,
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }
            if (filteredText != newText) {
                onTextChange(filteredText)
            } else {
                onTextChange(newText)
            }
        },
        singleLine = true,
        cursorBrush = SolidColor(Blue),
        textStyle = LocalAndroidDiplomaTypography.current.regular16,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number, imeAction = ImeAction.Search
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
                        // Placeholder
                        if (salaryText.isEmpty()) {
                            Text(
                                text = stringResource(R.string.enter_amount),
                                style = LocalAndroidDiplomaTypography.current.regular16,
                                color = LocalAndroidDiplomaScheme.current.salaryInput.placeHolder
                            )
                        }
                        innerTextField()
                    }
                }
                // Кнопка очистки
                if (salaryText.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null,
                        tint = LocalAndroidDiplomaScheme.current.salaryInput.clearIcon,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(end = Dimens.padding16)
                            .clickable {
                                onTextChange("")
                            })
                }
            }

        })
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewApproveButtonDay() {
    var text by remember { mutableStateOf("") }
    AndroidDiplomaTheme {
        SearchTextField(
            salaryText = text,
            onTextChange = { newText ->
                text = newText
            })
    }
}
