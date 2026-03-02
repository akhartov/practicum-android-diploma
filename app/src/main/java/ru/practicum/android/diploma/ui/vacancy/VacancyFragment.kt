package ru.practicum.android.diploma.ui.vacancy

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

class VacancyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            VacancyScreen()
        }
    }
}

@Composable
private fun VacancyScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        ToolBar()
        Title()
        EmployerInfo()
        VacancyInfo()
    }
}

@Composable
private fun Title() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = Dimens.padding24,
                start = Dimens.padding16,
                end = Dimens.padding16,
                bottom = Dimens.padding24
            )
    ) {
        Text(
            text = "Android разработчик",
            modifier = Modifier
                .wrapContentWidth(),
            color = LocalAndroidDiplomaScheme.current.vacancy.title,
            style = LocalAndroidDiplomaTypography.current.bold32
        )

        Text(
            text = "От 100 000 Р",
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = Dimens.padding4),
            color = LocalAndroidDiplomaScheme.current.vacancy.subTitle,
            style = LocalAndroidDiplomaTypography.current.medium22
        )
    }
}

@Composable
private fun EmployerInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.companyBarHeight)
            .padding(horizontal = Dimens.padding16)
            .background(
                color = LocalAndroidDiplomaScheme.current.vacancy.employerBg,
                shape = RoundedCornerShape(Dimens.cornerRadius12)
            )
            .padding(horizontal = Dimens.padding16)
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.imageSize)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = null,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(Dimens.cornerRadius12)),
                placeholder = painterResource(R.drawable.placeholder_vacancy_logo),
                error = painterResource(R.drawable.placeholder_vacancy_logo),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = Dimens.padding8, end = Dimens.padding16)
        ) {
            Text(
                text = "Еда",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentWidth(),
                color = LocalAndroidDiplomaScheme.current.vacancy.company,
                style = LocalAndroidDiplomaTypography.current.medium22
            )

            Text(
                text = "Москва",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentWidth(),
                color = LocalAndroidDiplomaScheme.current.vacancy.company,
                style = LocalAndroidDiplomaTypography.current.regular16
            )
        }
    }
}

@Composable
private fun VacancyInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = Dimens.padding16, top = Dimens.padding24, end = Dimens.padding16)
    ) {
        Text(
            text = stringResource(R.string.required_experience),
            modifier = Modifier
                .wrapContentWidth(),
            color = LocalAndroidDiplomaScheme.current.topBar.text,
            style = LocalAndroidDiplomaTypography.current.medium16
        )
        Text(
            text = stringResource(R.string.required_experience),
            modifier = Modifier
                .wrapContentWidth(),
            color = LocalAndroidDiplomaScheme.current.topBar.text,
            style = LocalAndroidDiplomaTypography.current.regular16
        )

        Text(
            text = "Полная занятость, полный день",
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = Dimens.padding8),
            color = LocalAndroidDiplomaScheme.current.topBar.text,
            style = LocalAndroidDiplomaTypography.current.regular16
        )

        Text(
            text = stringResource(R.string.description_vacancy),
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = Dimens.padding32),
            color = LocalAndroidDiplomaScheme.current.topBar.text,
            style = LocalAndroidDiplomaTypography.current.medium22
        )
    }
}

@Composable
private fun ToolBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.toolBarHeight)
            .padding(horizontal = Dimens.padding8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = null,
            tint = LocalAndroidDiplomaScheme.current.topBar.text,
            modifier = Modifier
                .wrapContentWidth()
        )

        Text(
            text = stringResource(R.string.vacancy),
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = Dimens.padding4),
            color = LocalAndroidDiplomaScheme.current.topBar.text,
            style = LocalAndroidDiplomaTypography.current.medium22
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(R.drawable.ic_share),
            contentDescription = null,
            tint = LocalAndroidDiplomaScheme.current.topBar.text,
            modifier = Modifier
                .wrapContentWidth()
        )
        Icon(
            painter = painterResource(R.drawable.ic_favourite),
            contentDescription = null,
            tint = LocalAndroidDiplomaScheme.current.topBar.text,
            modifier = Modifier
                .wrapContentWidth()
        )
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VacancyScreenPreview() {
    AndroidDiplomaTheme {
        VacancyScreen()
    }
}

