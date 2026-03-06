package ru.practicum.android.diploma.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography
import ru.practicum.android.diploma.ui.theme.SearchFieldColors

@Composable
fun SearchTextField(
    searchQuery: String,
    placeholder: String,
    onQueryChange: (String) -> Unit,
    onClearQuery: () -> Unit,
    searchTextFieldType: SearchTextFieldType = SearchTextFieldType.Primary
) {
    val customSearchTextFieldColors = LocalAndroidDiplomaScheme.current.searchField
    val searchTextFieldColors = searchTextFieldType.getSearchTextFieldColors(customSearchTextFieldColors)

    BasicTextField(
        value = searchQuery,
        onValueChange = { onQueryChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.searchBarHeight),
        textStyle = LocalAndroidDiplomaTypography.current.regular16.copy(
            color = searchTextFieldColors.textColor
        ),
        singleLine = true,
        cursorBrush = SolidColor(searchTextFieldColors.cursorColor),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(
                        searchTextFieldColors.backgroundColor,
                        RoundedCornerShape(Dimens.cornerRadius12)
                    )
                    .padding(horizontal = Dimens.padding16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = Dimens.padding8)
                ) {
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = searchTextFieldColors.hintColor,
                            style = LocalAndroidDiplomaTypography.current.regular16
                        )
                    }
                    innerTextField()
                }
                if (searchQuery.isEmpty()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = Dimens.padding20),
                        tint = searchTextFieldColors.iconColor
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = Dimens.padding20)
                            .clickable { onClearQuery() },
                        tint = searchTextFieldColors.iconColor
                    )
                }
            }
        }
    )
}

sealed class SearchTextFieldType {
    abstract fun getSearchTextFieldColors(customSearchFieldColors: SearchFieldColors): SearchTextFieldConfig

    data object Primary : SearchTextFieldType() {
        override fun getSearchTextFieldColors(customSearchFieldColors: SearchFieldColors): SearchTextFieldConfig {
            return SearchTextFieldConfig(
                backgroundColor = customSearchFieldColors.background,
                textColor = customSearchFieldColors.text,
                hintColor = customSearchFieldColors.hint,
                cursorColor = customSearchFieldColors.cursor,
                iconColor = customSearchFieldColors.icon
            )
        }
    }
}

data class SearchTextFieldConfig(
    val backgroundColor: Color,
    val textColor: Color,
    val hintColor: Color,
    val cursorColor: Color,
    val iconColor: Color
)
