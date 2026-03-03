package ru.practicum.android.diploma.ui.vacancy

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.Contacts
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.presentation.model.VacancyDetailsState
import ru.practicum.android.diploma.presentation.vacancy.VacancyViewModel
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

        val id = arguments?.getString(SELECTED_VACANCY) ?: ""
        setContent {
            VacancyScreen(vacancyViewModel = koinViewModel(), id)
        }
    }

    companion object {
        private const val SELECTED_VACANCY = "selected_vacancy"

        fun createArgs(id: String): Bundle = bundleOf(SELECTED_VACANCY to id)
    }
}

@Composable
private fun VacancyScreen(vacancyViewModel: VacancyViewModel, id: String) {
    vacancyViewModel.getVacancy(id)
    when (val state = vacancyViewModel.state.collectAsState().value) {
        is VacancyDetailsState.Loading -> {
            // Состояние загрузки
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingView()
            }
        }

        is VacancyDetailsState.NotFound -> {
            ErrorNotFound()
        }

        is VacancyDetailsState.ServerError -> {
            ErrorServer()
        }

        is VacancyDetailsState.NoInternet -> {
            ErrorNoInternet()
        }

        is VacancyDetailsState.Content -> {
            // Успешно загруженные данные
            VacancyDetailContent(
                vacancy = state.details,
                /*onBackPressed = { *//* ... */
                /* },
                                onShare = { */
                /* ... *//* }*/
            )
        }
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(44.dp),
            color = LocalAndroidDiplomaScheme.current.vacancy.progress,
            strokeWidth = 4.dp,
        )
    }
}

@Composable
private fun ErrorServer() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding200)
            )
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.placeholder_vacancy_server_error),
                contentDescription = null,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding16)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.server_error),
                textAlign = TextAlign.Center,
                color = LocalAndroidDiplomaScheme.current.vacancy.company,
                style = LocalAndroidDiplomaTypography.current.medium16
            )
        }
    }
}

@Composable
private fun ErrorNotFound() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding186)
            )
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.placeholder_no_vacancy),
                contentDescription = null,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding16)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.vacancies_not_found),
                textAlign = TextAlign.Center,
                color = LocalAndroidDiplomaScheme.current.vacancy.company,
                style = LocalAndroidDiplomaTypography.current.medium16
            )
        }
    }
}

@Composable
private fun ErrorNoInternet() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding186)
            )
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.placeholder_no_internet),
                contentDescription = null,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding16)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.no_internet),
                textAlign = TextAlign.Center,
                color = LocalAndroidDiplomaScheme.current.vacancy.company,
                style = LocalAndroidDiplomaTypography.current.medium16
            )
        }
    }
}

@Composable
private fun VacancyDetailContent(vacancy: Vacancy) {
    Column(modifier = Modifier.fillMaxSize()) {
        ToolBar()
        Title(vacancy)
        EmployerInfo(vacancy)
        VacancyInfo(vacancy)
    }
}

@Composable
private fun Title(vacancy: Vacancy) {
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
            text = vacancy.name,
            modifier = Modifier
                .wrapContentWidth(),
            color = LocalAndroidDiplomaScheme.current.vacancy.title,
            style = LocalAndroidDiplomaTypography.current.bold32
        )
        if (vacancy.salaryString != null) {
            Text(
                text = vacancy.salaryString,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = Dimens.padding4),
                color = LocalAndroidDiplomaScheme.current.vacancy.subTitle,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
        }
    }
}

@Composable
private fun EmployerInfo(vacancy: Vacancy) {
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
                model = vacancy.employerLogoUrl,
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
            vacancy.employerName?.let {
                Text(
                    text = it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .wrapContentWidth(),
                    color = LocalAndroidDiplomaScheme.current.vacancy.company,
                    style = LocalAndroidDiplomaTypography.current.medium22
                )
            }

            if (vacancy.address != null) {
                Text(
                    text = vacancy.address,
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
}

@Composable
private fun VacancyInfo(vacancy: Vacancy) {
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
            text = stringResource(R.string.key_skills),
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = Dimens.padding24),
            color = LocalAndroidDiplomaScheme.current.topBar.text,
            style = LocalAndroidDiplomaTypography.current.medium22
        )

        if (vacancy.description != null) {
            Description(vacancy.description)
        }

        if (vacancy.skills != null) {
            Skills(vacancy.skills)
        }

    }
}

@Composable
private fun Description(description: String) {
    Text(
        text = stringResource(R.string.description_vacancy),
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = Dimens.padding32),
        color = LocalAndroidDiplomaScheme.current.topBar.text,
        style = LocalAndroidDiplomaTypography.current.medium22
    )

    Text(
        text = description,
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = Dimens.padding16),
        color = LocalAndroidDiplomaScheme.current.topBar.text,
        style = LocalAndroidDiplomaTypography.current.regular16
    )
}

@Composable
private fun Contacts(contacts: Contacts) {
    Text(
        text = stringResource(R.string.contacts),
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = Dimens.padding32),
        color = LocalAndroidDiplomaScheme.current.topBar.text,
        style = LocalAndroidDiplomaTypography.current.medium22
    )

    Text(
        text = contacts.name,
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = Dimens.padding16),
        color = LocalAndroidDiplomaScheme.current.topBar.text,
        style = LocalAndroidDiplomaTypography.current.regular16
    )

    Text(
        text = listOf(stringResource(R.string.email), contacts.email).joinToString(" "),
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = Dimens.padding16),
        color = LocalAndroidDiplomaScheme.current.topBar.text,
        style = LocalAndroidDiplomaTypography.current.regular16
    )

    contacts.phone.forEach { phone ->
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = phone,
                color = LocalAndroidDiplomaScheme.current.topBar.text,
                style = LocalAndroidDiplomaTypography.current.regular16
            )
        }
    }
}

@Composable
private fun Skills(skills: List<String>) {
    Column(
        modifier = Modifier.padding(top = Dimens.padding16)
    ) {
        skills.forEach { skill ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.dot),
                    modifier = Modifier.padding(horizontal = Dimens.padding4),
                    color = LocalAndroidDiplomaScheme.current.topBar.text,
                    style = LocalAndroidDiplomaTypography.current.regular16
                )

                Text(
                    text = skill,
                    color = LocalAndroidDiplomaScheme.current.topBar.text,
                    style = LocalAndroidDiplomaTypography.current.regular16
                )
            }
        }
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
        //VacancyScreen()
    }
}

