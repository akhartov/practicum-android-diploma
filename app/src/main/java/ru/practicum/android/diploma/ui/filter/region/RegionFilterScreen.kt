package ru.practicum.android.diploma.ui.filter.region

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.AreaShort
import ru.practicum.android.diploma.domain.models.Region
import ru.practicum.android.diploma.presentation.filter.region.RegionFilterState
import ru.practicum.android.diploma.ui.common.FilterSectionControlType
import ru.practicum.android.diploma.ui.common.FilterSelectionControl
import ru.practicum.android.diploma.ui.common.FilterTopAppBar
import ru.practicum.android.diploma.ui.common.SearchTextField
import ru.practicum.android.diploma.ui.theme.AndroidDiplomaTheme
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
private fun NoRegion() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding136)
            )
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.placeholder_empty_search),
                contentDescription = null,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding16)
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.no_region),
                textAlign = TextAlign.Center,
                color = LocalAndroidDiplomaScheme.current.vacancy.title,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
        }
    }
}

@Composable
private fun NoInternet() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding136)
            )
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.placeholder_no_internet),
                contentDescription = null,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding16)
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.no_internet),
                textAlign = TextAlign.Center,
                color = LocalAndroidDiplomaScheme.current.vacancy.title,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
        }
    }
}

@Composable
private fun GetListError() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding122)
            )
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.placeholder_region_error),
                contentDescription = null,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding16)
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.region_selection_error),
                textAlign = TextAlign.Center,
                color = LocalAndroidDiplomaScheme.current.vacancy.title,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
        }
    }
}

@Composable
fun RegionFilterScreen(
    onBackClick: () -> Unit,
    regionFilter: StateFlow<RegionFilterState>,
    onRegionClick: ((Region) -> Unit),
    searchQuery: StateFlow<String>,
    onQueryChange: (String) -> Unit,
    onClearQuery: () -> Unit,
) {
    val regionFilterState = regionFilter.collectAsState()
    val searchQueryState = searchQuery.collectAsState()
    Scaffold(
        topBar = {
            FilterTopAppBar(
                title = stringResource(R.string.region_selection),
                onBackClick = { onBackClick() }
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
                searchQuery = searchQueryState.value,
                placeholder = stringResource(R.string.enter_area),
                onQueryChange = onQueryChange,
                onClearQuery = onClearQuery,
            )

            when (regionFilterState.value) {
                is RegionFilterState.Content -> {
                    Spacer(Modifier.height(Dimens.padding16))
                    RegionList(
                        (regionFilterState.value as RegionFilterState.Content).regions,
                        onRegionClick
                    )
                }

                RegionFilterState.Loading -> LoadingView()
                RegionFilterState.NoInternet -> NoInternet()
                RegionFilterState.NotFound -> NoRegion()
                RegionFilterState.ServerError -> GetListError()
            }
        }
    }
}

@Composable
private fun RegionList(areas: List<Region>, onRegionClick: ((Region) -> Unit)?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(areas) { region ->
            FilterSelectionControl(
                Modifier,
                onClick = { onRegionClick?.let { it(region) } },
                filterSectionControlType = FilterSectionControlType.FixedMenu,
                text = region.region.name,
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
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewSearchRegion() {
    val text = MutableStateFlow("")
    val regions = listOf(
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Москва")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Санкт-Петербург")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Новосибирск")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Екатеринбург")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Нижний Новгород")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Челябинск")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Самара")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Омск")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Ростов-на-Дону")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Нижний Новгород")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Челябинск")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Самара")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Омск")),
        Region(AreaShort(id = 1, name = "Россия"), AreaShort(id = 10, name = "Ростов-на-Дону")),
    )

    val state = MutableStateFlow<RegionFilterState>(RegionFilterState.Content(regions))
    AndroidDiplomaTheme {
        RegionFilterScreen({}, state, {}, text, {}, {})
    }
}
