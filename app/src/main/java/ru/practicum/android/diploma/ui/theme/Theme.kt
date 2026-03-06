package ru.practicum.android.diploma.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    background = White,
    onBackground = Black,
    primary = Blue,
    onPrimary = White,
    secondary = LightGray,
    onSecondary = Black
)

val DarkColorScheme = darkColorScheme(
    background = Black,
    onBackground = White,
    primary = Blue,
    onPrimary = White,
    secondary = Gray,
    onSecondary = Black
)

private val LightCustomScheme = AndroidDiplomaScheme(
    topBar = TopBarStateColors(
        text = Black,
        icon = Black
    ),
    searchField = SearchFieldColors(
        background = LightGray,
        text = Black,
        hint = Gray,
        cursor = Blue,
        icon = Black
    ),
    buttonColors = ButtonColors(
        approve = ButtonElementColors(
            background = Blue,
            text = White
        ),
        decline = ButtonElementColors(
            background = White,
            text = Red
        ),
    ),
    vacancy = VacancyColors(
        title = Black,
        subTitle = Black,
        logo = LightGray,
        employerBg = LightGray,
        company = Black,
        progress = Blue,
        like = Red
    ),
    results = ResultsColors(
        background = Blue,
        text = White
    ),
    filterIcon = FilterIconColors(
        hasFilterIcon = FilterIconState(
            background = Blue,
            hint = White
        ),
        noFilterIcon = FilterIconState(
            background = White,
            hint = Black
        )
    )
)

private val DarkCustomScheme = AndroidDiplomaScheme(
    topBar = TopBarStateColors(
        text = White,
        icon = White
    ),
    searchField = SearchFieldColors(
        background = Gray,
        text = Black,
        hint = White,
        cursor = Blue,
        icon = Black
    ),
    buttonColors = ButtonColors(
        approve = ButtonElementColors(
            background = Blue,
            text = White
        ),
        decline = ButtonElementColors(
            background = Black,
            text = Red
        ),
    ),
    vacancy = VacancyColors(
        title = White,
        subTitle = White,
        logo = LightGray,
        employerBg = LightGray,
        company = Black,
        progress = Blue,
        like = Red
    ),
    results = ResultsColors(
        background = Blue,
        text = White
    ),
    filterIcon = FilterIconColors(
        hasFilterIcon = FilterIconState(
            background = Blue,
            hint = White
        ),
        noFilterIcon = FilterIconState(
            background = Black,
            hint = White
        )
    )
)

data class FilterIconColors(
    val hasFilterIcon: FilterIconState,
    val noFilterIcon: FilterIconState
)

data class FilterIconState(
    val background: Color,
    val hint: Color
)

data class AndroidDiplomaScheme(
    val topBar: TopBarStateColors,
    val searchField: SearchFieldColors,
    val buttonColors: ButtonColors,
    val vacancy: VacancyColors,
    val results: ResultsColors,
    val filterIcon: FilterIconColors
)

data class ButtonColors(
    val approve: ButtonElementColors,
    val decline: ButtonElementColors,
)

data class ButtonElementColors(
    val background: Color,
    val text: Color
)

// Результат поиска(количество найденных вакансий)
data class ResultsColors(
    val background: Color,
    val text: Color
)

data class VacancyColors(
    val title: Color,
    val subTitle: Color,
    val logo: Color,
    val employerBg: Color,
    val company: Color,
    val progress: Color,
    val like: Color
)

data class SearchFieldColors(
    val background: Color,
    val text: Color,
    val hint: Color,
    val cursor: Color,
    val icon: Color
)

data class TopBarStateColors(
    val text: Color,
    val icon: Color
)

val LocalAndroidDiplomaScheme = staticCompositionLocalOf<AndroidDiplomaScheme> {
    error("No AndroidDiplomaScheme provided")
}

val LocalAndroidDiplomaTypography = staticCompositionLocalOf<AndroidDiplomaTypography> {
    error("No AndroidDiplomaTypography provided")
}

@Composable
fun AndroidDiplomaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val customScheme = when {
        darkTheme -> DarkCustomScheme
        else -> LightCustomScheme
    }

    CompositionLocalProvider(
        LocalAndroidDiplomaScheme provides customScheme,
        LocalAndroidDiplomaTypography provides Typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}
