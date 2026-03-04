package ru.practicum.android.diploma.ui.vacancy

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.model.LikeButtonState
import ru.practicum.android.diploma.presentation.model.VacancyDetailsState
import ru.practicum.android.diploma.presentation.vacancy.VacancyViewModel
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun VacancyScreen(vacancyViewModel: VacancyViewModel, onBack: () -> Unit) {
    val likeButtonState = vacancyViewModel.stateLike.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ToolBar(likeButtonState, vacancyViewModel, onBack)

        when (val state = vacancyViewModel.state.collectAsState().value) {
            is VacancyDetailsState.Loading -> {
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
                    sendMail = { data -> vacancyViewModel.sendMail(data) },
                    onCall = { number -> vacancyViewModel.callNumber(number) }
                )
            }
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
                color = LocalAndroidDiplomaScheme.current.vacancy.title,
                style = LocalAndroidDiplomaTypography.current.medium22
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
                color = LocalAndroidDiplomaScheme.current.vacancy.title,
                style = LocalAndroidDiplomaTypography.current.medium22
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
                color = LocalAndroidDiplomaScheme.current.vacancy.title,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
        }
    }
}

@Composable
private fun ToolBar(likeState: LikeButtonState, vacancyViewModel: VacancyViewModel, onBack: () -> Unit) {
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
                .clickable { onBack() }
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
                .clickable { vacancyViewModel.shareLink() }
        )
        Icon(
            painter = when (likeState) {
                LikeButtonState.Like -> painterResource(R.drawable.ic_favorites)
                LikeButtonState.UnLike -> painterResource(R.drawable.ic_favorite)
            },
            contentDescription = null,
            tint = when (likeState) {
                LikeButtonState.Like -> LocalAndroidDiplomaScheme.current.vacancy.like
                LikeButtonState.UnLike -> LocalAndroidDiplomaScheme.current.topBar.text
            },
            modifier = Modifier
                .wrapContentWidth()
                .clickable { vacancyViewModel.like() }
        )

    }
}
