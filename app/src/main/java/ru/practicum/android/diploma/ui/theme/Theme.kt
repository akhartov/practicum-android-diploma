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
    vacancy = VacancyColors(
        title = Black,
        subTitle = Black
    ),
    results = ResultsColors(
        background = Blue,
        text = White
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
        icon = Blue
    ),
    vacancy = VacancyColors(
        title = White,
        subTitle = White
    ),
    results = ResultsColors(
        background = Blue,
        text = White
    )
)

data class AndroidDiplomaScheme(
    val topBar: TopBarStateColors,
    val searchField: SearchFieldColors,
    val vacancy: VacancyColors,
    val results: ResultsColors
)

// Результат поиска(количество найденных вакансий)
data class ResultsColors(
    val background: Color,
    val text: Color
)

data class VacancyColors(
    val title: Color,
    val subTitle: Color
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
fun AndoroidDiplomaTheme(
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
