package id.belitong.bigs.compose.ui.screen.details.geosite

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.Geosite
import id.belitong.bigs.compose.core.utils.DummyData
import id.belitong.bigs.compose.core.utils.showToast
import id.belitong.bigs.compose.ui.composable.components.ChipGroupSingleSelection
import id.belitong.bigs.compose.ui.composable.components.GeositeListItem
import id.belitong.bigs.compose.ui.composable.model.ChipFilter
import id.belitong.bigs.compose.ui.composable.model.getGeositesFilter
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.typography

@Composable
fun GeositesScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val selectedChip = remember { mutableStateOf(ChipFilter.NULL) }
    val listState = rememberLazyListState()

    val geosites = DummyData.getAllGeosites()
    var chipData by remember { mutableStateOf(geosites) }

    chipData = when (selectedChip.value) {
        ChipFilter.ALPHABET -> {
            geosites.sortedBy { it.name }
        }

        ChipFilter.NEAREST -> {
            geosites.sortedBy { it.distance }
        }

        ChipFilter.NULL -> {
            geosites
        }

        else -> {
            geosites
        }
    }

    LaunchedEffect(selectedChip.value) {
        listState.animateScrollToItem(0)
    }

    GeositesScreenContent(
        modifier = modifier,
        geosites = chipData,
        selectedChip = selectedChip,
        listState = listState,
        onBackPressed = { onBackPressedDispatcher?.onBackPressed() },
        onItemClicked = { context.getString(R.string.on_click_handler).showToast(context) },
    )
}

@Composable
fun GeositesScreenContent(
    modifier: Modifier = Modifier,
    geosites: List<Geosite> = emptyList(),
    selectedChip: MutableState<ChipFilter> = remember { mutableStateOf(ChipFilter.NULL) },
    listState: LazyListState = rememberLazyListState(),
    onBackPressed: () -> Unit = {},
    onItemClicked: (Geosite) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimension.SIZE_16),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_16, bottom = Dimension.SIZE_26),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .clickable(
                        onClick = onBackPressed
                    ),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(R.string.back),
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.title_activity_geosites),
                style = typography.h3,
                color = Color.Black.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
            )
        }
        ChipGroupSingleSelection(
            modifier = Modifier.fillMaxWidth(),
            listFilter = getGeositesFilter(),
            selectedChip = selectedChip.value,
            onChipSelected = {
                selectedChip.value = it

            },
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimension.SIZE_8),
            verticalArrangement = Arrangement.spacedBy(Dimension.SIZE_8),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(Dimension.SIZE_0),
            state = listState,
        ) {
            items(geosites) { geosite ->
                GeositeListItem(
                    geosite = geosite,
                    onItemClicked = { onItemClicked(geosite) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GeositesScreenPreview() {
    GeositesScreenContent()
}
