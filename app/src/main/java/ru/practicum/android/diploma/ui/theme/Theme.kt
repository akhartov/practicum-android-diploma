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
    checkboxColors = CheckboxColors(
        filter = CheckboxElementColors(
            text = Black,
            tint = Blue,
            checkmark = White
        )
    ),
    filterSelectionColors = FilterSelectionColors(
        presents = FilterSelectionElementColors(
            background = White,
            text = Black,
            icon = Black,
            hint = Gray,
        ),
        absent = FilterSelectionElementColors(
            background = White,
            text = Gray,
            icon = Black,
            hint = Gray,
        ),
        fixed = FilterSelectionElementColors(
            background = White,
            text = Black,
            icon = Black,
            hint = Black,
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
    radioButton = RadioButtonColors(
        text = Black,
        tint = Blue
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
    ),
    salaryInput = SalaryInputColors(
        background = LightGray,
        clearIcon = Black,
        placeHolder = Gray,
        brush = Blue
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
    checkboxColors = CheckboxColors(
        filter = CheckboxElementColors(
            text = White,
            tint = Blue,
            checkmark = Black,
        )
    ),
    filterSelectionColors = FilterSelectionColors(
        presents = FilterSelectionElementColors(
            background = Black,
            text = White,
            icon = White,
            hint = Gray,
        ),
        absent = FilterSelectionElementColors(
            background = Black,
            text = White,
            icon = White,
            hint = Gray
        ),
        fixed = FilterSelectionElementColors(
            background = Black,
            text = White,
            icon = White,
            hint = White,
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
    radioButton = RadioButtonColors(
        text = White,
        tint = Blue
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
    ),
    salaryInput = SalaryInputColors(
        background = Gray,
        clearIcon = Black,
        placeHolder = White,
        brush = Blue
    )
)

data class FilterIconColors(
    val hasFilterIcon: FilterIconState,
    val noFilterIcon: FilterIconState
)

data class SalaryInputColors(
    val background: Color,
    val clearIcon: Color,
    val placeHolder: Color,
    val brush: Color
)

data class FilterIconState(
    val background: Color,
    val hint: Color
)

data class AndroidDiplomaScheme(
    val topBar: TopBarStateColors,
    val searchField: SearchFieldColors,
    val buttonColors: ButtonColors,
    val checkboxColors: CheckboxColors,
    val filterSelectionColors: FilterSelectionColors,
    val vacancy: VacancyColors,
    val radioButton: RadioButtonColors,
    val results: ResultsColors,
    val filterIcon: FilterIconColors,
    val salaryInput: SalaryInputColors
)

data class ButtonColors(
    val approve: ButtonElementColors,
    val decline: ButtonElementColors,
)

data class ButtonElementColors(
    val background: Color,
    val text: Color
)

data class CheckboxColors(
    val filter: CheckboxElementColors,
)

data class CheckboxElementColors(
    val text: Color,
    val tint: Color,
    val checkmark: Color,
)

data class FilterSelectionColors(
    val presents: FilterSelectionElementColors, // фильтр указан
    val absent: FilterSelectionElementColors, // фильтр не указан
    val fixed: FilterSelectionElementColors, // не фильтр, а пункт меню фильтрации
)

data class FilterSelectionElementColors(
    val background: Color,
    val text: Color,
    val icon: Color,
    val hint: Color,
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

data class RadioButtonColors(
    val text: Color,
    val tint: Color,
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
