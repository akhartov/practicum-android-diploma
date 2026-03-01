package ru.practicum.android.diploma.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.VacancyShort
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography
import ru.practicum.android.diploma.ui.theme.VacancyColors

@Composable
fun VacancyItem(
    vacancy: VacancyShort,
    onClick: () -> Unit,
    vacancyItemType: VacancyItemType = VacancyItemType.Primary
) {
    val customVacancyItemColors = LocalAndroidDiplomaScheme.current.vacancy
    val vacancyItemColors = vacancyItemType.getVacancyItemColors(customVacancyItemColors)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = Dimens.padding9),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.logoSize)
                .border(
                    width = Dimens.logoBorderThickness,
                    color = vacancyItemColors.logoColor,
                    shape = RoundedCornerShape(Dimens.cornerRadius12)
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = vacancy.employerLogoUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(Dimens.cornerRadius12)),
                placeholder = painterResource(R.drawable.placeholder_vacancy_logo),
                error = painterResource(R.drawable.placeholder_vacancy_logo),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.width(Dimens.padding12))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = vacancy.vacancyTitle ?: "",
                color = vacancyItemColors.titleColor,
                maxLines = 3,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
            Spacer(modifier = Modifier.height(Dimens.padding4))
            Text(
                text = vacancy.employerName ?: "",
                color = vacancyItemColors.subTitleColor,
                style = LocalAndroidDiplomaTypography.current.regular16
            )
            Text(
                text = vacancy.salaryString ?: "",
                color = vacancyItemColors.subTitleColor,
                style = LocalAndroidDiplomaTypography.current.regular16
            )
        }
    }
}

sealed class VacancyItemType {
    abstract fun getVacancyItemColors(customVacancyColors: VacancyColors): VacancyItemConfig

    data object Primary : VacancyItemType() {
        override fun getVacancyItemColors(customVacancyColors: VacancyColors): VacancyItemConfig {
            return VacancyItemConfig(
                titleColor = customVacancyColors.title,
                subTitleColor = customVacancyColors.subTitle,
                logoColor = customVacancyColors.logo
            )
        }
    }
}

data class VacancyItemConfig(
    val titleColor: Color,
    val subTitleColor: Color,
    val logoColor: Color
)
