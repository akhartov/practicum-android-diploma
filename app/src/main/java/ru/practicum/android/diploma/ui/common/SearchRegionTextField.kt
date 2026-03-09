package ru.practicum.android.diploma.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.Blue
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun SearchRegionTextField(
    setRegionValue: (Int?) -> Unit,
    regionText: String
) {
    var isFocused by remember { mutableStateOf(false) }
    var textState by remember { mutableStateOf(regionText) }

    LaunchedEffect(regionText) {
        if (textState != regionText) {
            textState = regionText
        }
    }

    BasicTextField(
        value = textState,
        onValueChange = { newText ->
            if (newText.isEmpty()) {
                // setRegionValue(null)
            } else {
                // setRegionValue(newText)
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
                    .background(LocalAndroidDiplomaScheme.current.salaryInput.background)
                    .padding(start = Dimens.padding16)
                    .onFocusEvent { focusState ->
                        isFocused = focusState.hasFocus
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Контейнер для текста
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = Dimens.padding16)
                ) {
                    PlaceholderText(regionText.isEmpty())
                    innerTextField()
                }
                SearchIcon(regionText.isEmpty())

                ClearButton(
                    regionText.isNotEmpty(),
                    onClearClick = {
                        textState = ""
                        setRegionValue(null)
                    }
                )
            }
        }
    )
}

@Composable
private fun SearchIcon(
    show: Boolean
) {
    if (show) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            tint = LocalAndroidDiplomaScheme.current.salaryInput.clearIcon,
            modifier = Modifier
                .wrapContentSize()
                .padding(end = Dimens.padding16)
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

@Composable
private fun PlaceholderText(
    show: Boolean
) {
    if (show) {
        Text(
            text = stringResource(R.string.enter_area),
            style = LocalAndroidDiplomaTypography.current.regular16,
            color = LocalAndroidDiplomaScheme.current.salaryInput.placeHolder
        )
    }
}
