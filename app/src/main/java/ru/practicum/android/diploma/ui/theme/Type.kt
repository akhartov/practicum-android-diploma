package ru.practicum.android.diploma.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = AndroidDiplomaTypography(
    bold32 = TextStyle(
        fontFamily = YandexDisplayFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    medium22 = TextStyle(
        fontFamily = YandexDisplayFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    medium16 = TextStyle(
        fontFamily = YandexDisplayFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    regular16 = TextStyle(
        fontFamily = YandexDisplayFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    regular12 = TextStyle(
        fontFamily = YandexDisplayFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),

)

data class AndroidDiplomaTypography(
    val bold32: TextStyle,
    val medium22: TextStyle,
    val medium16: TextStyle,
    val regular16: TextStyle,
    val regular12: TextStyle
)
