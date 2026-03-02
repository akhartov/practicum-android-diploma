package ru.practicum.android.diploma.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.model.VacanciesState
import ru.practicum.android.diploma.ui.common.VacancyItem
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun SearchContent(
    state: VacanciesState.Content,
    onVacancyItemClick: (String) -> Unit,
    onLoadNextPage: () -> Unit
) {
    val listState = rememberLazyListState()

    val shouldLoadNext by remember {
        derivedStateOf {
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            lastVisibleItemIndex >= totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadNext) {
        if (shouldLoadNext) {
            onLoadNextPage()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            item(key = "spacer") {
                Spacer(modifier = Modifier.height(Dimens.padding38))
            }

            items(state.vacancies) { vacancy ->
                VacancyItem(
                    vacancy = vacancy,
                    onClick = { onVacancyItemClick(vacancy.id) }
                )
            }
//            if (state.isLoadingNextPage) {
//            if (true) {
            if (false) {
                item(key = "loading_indicator") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Dimens.padding16),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            Modifier.size(Dimens.progressIndicatorSize)
                        )
                    }
                }
            }
        }

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
//                    text = pluralStringResource(R.plurals.found_n_vacancies, state.found, state.found),
                    text = pluralStringResource(R.plurals.found_n_vacancies, 6, 6),
                    style = LocalAndroidDiplomaTypography.current.regular16,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = Dimens.padding12, vertical = Dimens.padding4)
                )
            }
        }
    }
}
