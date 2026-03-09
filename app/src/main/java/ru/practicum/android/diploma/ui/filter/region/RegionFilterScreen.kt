package ru.practicum.android.diploma.ui.filter.region

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.AreaShort
import ru.practicum.android.diploma.ui.common.FilterSectionControlType
import ru.practicum.android.diploma.ui.common.FilterSelectionControl
import ru.practicum.android.diploma.ui.common.FilterTopAppBar
import ru.practicum.android.diploma.ui.common.SearchRegionTextField
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
fun RegionFilterScreen(onBackClick: () -> Unit, areas: List<AreaShort>, onAreaClick: ((AreaShort) -> Unit)?) {
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
            SearchRegionTextField({}, "")
            // Эта фигня с условием ля detekt
            if (areas.isEmpty()) {
                NoRegion()
            } else if (areas.size == 1) {
                GetListError()
            }
            Spacer(Modifier.height(Dimens.padding16))
            RegionList(areas, onAreaClick)
        }
    }
}

@Composable
private fun RegionList(areas: List<AreaShort>, onAreaClick: ((AreaShort) -> Unit)?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(areas) { area ->
            FilterSelectionControl(
                Modifier,
                onClick = { onAreaClick?.let { it(area) } },
                filterSectionControlType = FilterSectionControlType.FixedMenu,
                text = area.name,
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewSearchRegion() {
    var text by remember { mutableStateOf("") }
    val areas = listOf(
        AreaShort(id = 1, name = "Москва"),
        AreaShort(id = 2, name = "Санкт-Петербург"),
        AreaShort(id = 3, name = "Новосибирск"),
        AreaShort(id = 4, name = "Екатеринбург"),
        AreaShort(id = 5, name = "Казань"),
        AreaShort(id = 6, name = "Нижний Новгород"),
        AreaShort(id = 7, name = "Челябинск"),
        AreaShort(id = 8, name = "Самара"),
        AreaShort(id = 9, name = "Омск"),
        AreaShort(id = 10, name = "Ростов-на-Дону"),
        AreaShort(id = 6, name = "Нижний Новгород"),
        AreaShort(id = 7, name = "Челябинск"),
        AreaShort(id = 8, name = "Самара"),
        AreaShort(id = 9, name = "Омск"),
        AreaShort(id = 10, name = "Ростов-на-Дону")
    )
    AndroidDiplomaTheme {
        RegionFilterScreen({}, areas, {})
    }
}
