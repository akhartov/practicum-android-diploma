package ru.practicum.android.diploma.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.model.VacanciesState
import ru.practicum.android.diploma.ui.common.SearchTextField
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun SearchScreen(
    state: VacanciesState,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onClearQuery: () -> Unit,
    onFilterIconClick: () -> Unit,
    onVacancyItemClick: (String) -> Unit,
    onLoadNextPage: () -> Unit,
    isSearchInProgress: StateFlow<Boolean>
) {
    Scaffold(
        topBar = {
            SearchTopAppBar(
                title = stringResource(R.string.search_vacancies),
                onFilterIconClick = onFilterIconClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Dimens.padding16)
        ) {
            SearchTextField(
                searchQuery = searchQuery,
                placeholder = stringResource(R.string.enter_search_query),
                onQueryChange = onQueryChange,
                onClearQuery = onClearQuery
            )
            Spacer(modifier = Modifier.height(Dimens.padding2))

            when (state) {
                is VacanciesState.Empty -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.placeholder_start_search),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                is VacanciesState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            Modifier.size(Dimens.progressIndicatorSize)
                        )
                    }
                }

                is VacanciesState.NotFound -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        SearchPlaceholder(
                            imageRes = R.drawable.placeholder_empty_search,
                            stringResource(R.string.cant_get_vacancies)
                        )
                        Box(
                            modifier = Modifier.align(Alignment.TopCenter),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = Dimens.padding9)
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                                    .background(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(Dimens.cornerRadius12)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.no_vacancies),
                                    style = LocalAndroidDiplomaTypography.current.regular16,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .padding(
                                            horizontal = Dimens.padding12,
                                            vertical = Dimens.padding4
                                        )
                                )
                            }
                        }
                    }
                }

                is VacanciesState.NoInternet,
                is VacanciesState.ServerError -> {
                    SearchPlaceholder(
                        imageRes = when (state) {
                            is VacanciesState.NoInternet -> R.drawable.placeholder_no_internet
                            else -> R.drawable.placeholder_server_error
                        },
                        title = when (state) {
                            is VacanciesState.NoInternet -> stringResource(R.string.no_internet)
                            else -> stringResource(R.string.server_error)
                        }
                    )
                }

                is VacanciesState.Content -> {
                    SearchContent(
                        state = state,
                        onVacancyItemClick = onVacancyItemClick,
                        onLoadNextPage = onLoadNextPage,
                        isSearchInProgress = isSearchInProgress
                    )
                }
            }
        }
    }
}

@Composable
fun SearchPlaceholder(
    imageRes: Int,
    title: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(Dimens.padding16))
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
        }
    }
}
