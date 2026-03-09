package ru.practicum.android.diploma.ui.filter.workplace

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.FilterIconColors
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme

@Composable
fun FilterIcon(
    filterIconType: FilterIconType,
    onClick: () -> Unit
) {
    val filterIconColors = LocalAndroidDiplomaScheme.current.filterIcon
    val filterIconConfig = filterIconType.getFilterColors(filterIconColors)
    IconButton(onClick = onClick) {
        Box(
            modifier = Modifier
                .size(Dimens.filterIconSize)
                .clip(RoundedCornerShape(Dimens.cornerRadius4))
                .background(filterIconConfig.background),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "",
                tint = filterIconConfig.hint,
            )
        }
    }
}

sealed class FilterIconType {

    abstract fun getFilterColors(customFilterIcon: FilterIconColors): FilterIconConfig

    object HasFilterIcon: FilterIconType() {
        override fun getFilterColors(customFilterIcon: FilterIconColors): FilterIconConfig {
            return FilterIconConfig(
                background = customFilterIcon.hasFilterIcon.background,
                hint = customFilterIcon.hasFilterIcon.hint
            )
        }
    }

    object NoFilterIcon : FilterIconType() {
        override fun getFilterColors(customFilterIcon: FilterIconColors): FilterIconConfig {
            return FilterIconConfig(
                background = customFilterIcon.noFilterIcon.background,
                hint = customFilterIcon.noFilterIcon.hint
            )
        }
    }
}

data class FilterIconConfig(
    val background: Color,
    val hint: Color
)
