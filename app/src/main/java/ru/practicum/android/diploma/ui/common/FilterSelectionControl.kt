package ru.practicum.android.diploma.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.FilterSelectionColors
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun FilterSelectionControl(
    modifier: Modifier,
    onClick: () -> Unit,
    filterSectionControlType: FilterSectionControlType,
    title: String? = null,
    text: String? = null,
) {
    val filterColors = filterSectionControlType.getConfig(LocalAndroidDiplomaScheme.current.filterSelectionColors)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Dimens.padding16),
    ) {
        Box(
            Modifier
                .height(Dimens.filterElementHeight)
                .weight(1f),
            contentAlignment = Alignment.CenterStart,
        ) {
            Column {
                filterColors.textColor?.let { color ->
                    title?.let { FilterSelectionTitle(title, color) }
                    text?.let { FilterSelectionText(text, color) }
                }

                filterColors.hintColor?.let { color ->
                    title?.let { FilterSelectionTitle(title, color) }
                    text?.let { FilterSelectionText(text, color) }
                }
            }
        }

        Box(
            Modifier
                .height(Dimens.filterElementHeight)
                .width(Dimens.filterElementHeight),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(filterColors.icondId),
                contentDescription = null,
                tint = filterColors.iconColor,
                modifier = modifier
                    .clickable { onClick() },
            )
        }
    }
}

@Composable
private fun FilterSelectionTitle(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = LocalAndroidDiplomaTypography.current.regular12
    )
}

@Composable
private fun FilterSelectionText(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = LocalAndroidDiplomaTypography.current.regular16
    )
}

sealed class FilterSectionControlType {
    abstract fun getConfig(customColors: FilterSelectionColors): Config

    data object Presents : FilterSectionControlType() {
        override fun getConfig(customColors: FilterSelectionColors): Config {
            return Config(
                backgroundColor = customColors.presents.background,
                textColor = customColors.presents.text,
                iconColor = customColors.presents.icon,
                icondId = R.drawable.ic_close,
            )
        }
    }

    data object Absent : FilterSectionControlType() {
        override fun getConfig(customColors: FilterSelectionColors): Config {
            return Config(
                backgroundColor = customColors.absent.background,
                iconColor = customColors.absent.icon,
                icondId = R.drawable.ic_arrow_forward,
                hintColor = customColors.absent.hint,
            )
        }
    }

    data object FixedMenu : FilterSectionControlType() {
        override fun getConfig(customColors: FilterSelectionColors): Config {
            return Config(
                backgroundColor = customColors.fixed.background,
                textColor = customColors.fixed.text,
                iconColor = customColors.fixed.icon,
                icondId = R.drawable.ic_arrow_forward,
            )
        }
    }

    data class Config(
        val backgroundColor: Color,
        val iconColor: Color,
        val icondId: Int,
        val textColor: Color? = null,
        val hintColor: Color? = null,
    )
}

private const val PREVIEW_WORKPLACE = "Место работы"
private const val PREVIEW_PITER = "Россия, Санкт-Петербург"
private const val PREVIEW_APRELEVKA = "Апрелевка"

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewPresentsFilterItemDay() {
    AndroidDiplomaTheme {
        FilterSelectionControl(
            Modifier,
            {},
            FilterSectionControlType.Presents,
            title = PREVIEW_WORKPLACE,
            text = PREVIEW_PITER,
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewAbsentFilterItemDay() {
    AndroidDiplomaTheme {
        FilterSelectionControl(
            Modifier,
            {},
            FilterSectionControlType.Absent,
            text = PREVIEW_WORKPLACE
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewPresentsFilterItemNight() {
    AndroidDiplomaTheme {
        FilterSelectionControl(
            Modifier,
            {},
            FilterSectionControlType.Presents,
            title = PREVIEW_WORKPLACE,
            text = PREVIEW_PITER,
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewAbsentFilterItemNight() {
    AndroidDiplomaTheme {
        FilterSelectionControl(
            Modifier,
            {},
            FilterSectionControlType.Absent,
            text = PREVIEW_WORKPLACE,
        )
    }
}

@Preview(
    device = "spec:width=480px,height=480px,dpi=420",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewPresentsFilterItemNightSmall() {
    AndroidDiplomaTheme {
        FilterSelectionControl(
            Modifier,
            {},
            FilterSectionControlType.Presents,
            title = PREVIEW_WORKPLACE,
            text = PREVIEW_PITER,
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewFixedFilterItemDay() {
    AndroidDiplomaTheme {
        FilterSelectionControl(
            Modifier,
            {},
            FilterSectionControlType.FixedMenu,
            text = PREVIEW_APRELEVKA
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=1820px,dpi=420",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewFixedFilterItemNight() {
    AndroidDiplomaTheme {
        FilterSelectionControl(
            Modifier,
            {},
            FilterSectionControlType.FixedMenu,
            text = PREVIEW_APRELEVKA,
        )
    }
}
