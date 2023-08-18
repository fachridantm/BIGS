package id.belitong.bigs.compose.ui.screen.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.Biodiversity
import id.belitong.bigs.compose.core.domain.model.Geosite
import id.belitong.bigs.compose.core.utils.DummyData
import id.belitong.bigs.compose.core.utils.getFirstName
import id.belitong.bigs.compose.core.utils.showToast
import id.belitong.bigs.compose.ui.composable.components.ButtonWithDrawableTop
import id.belitong.bigs.compose.ui.composable.components.CarouselPagerItem
import id.belitong.bigs.compose.ui.composable.components.ChipGroupSingleSelection
import id.belitong.bigs.compose.ui.composable.components.HomeGridItem
import id.belitong.bigs.compose.ui.composable.model.ChipFilter
import id.belitong.bigs.compose.ui.composable.model.getBiodiversityFilter
import id.belitong.bigs.compose.ui.composable.utils.getActivity
import id.belitong.bigs.compose.ui.navigation.MainNavGraph
import id.belitong.bigs.compose.ui.screen.details.geoprogramme.GeoprogrammeActivity
import id.belitong.bigs.compose.ui.screen.details.geosite.GeositesActivity
import id.belitong.bigs.compose.ui.screen.profile.ProfileActivity
import id.belitong.bigs.compose.ui.screen.search.SearchActivity
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.dark_Quaternary
import id.belitong.bigs.compose.ui.theme.md_theme_dark_secondary
import id.belitong.bigs.compose.ui.theme.md_theme_dark_tertiary
import id.belitong.bigs.compose.ui.theme.seed
import id.belitong.bigs.compose.ui.theme.typography

@MainNavGraph(true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator? = null, homeViewModel: HomeViewModel = hiltViewModel()
) {
    val activity = getActivity()
    val name = homeViewModel.getName().observeAsState()

    val selectedChip = remember { mutableStateOf(ChipFilter.ALL) }

    BackHandler {
        activity.finish()
    }

    val biodiversities = DummyData.getAllBiodiversity()
    var chipData by remember { mutableStateOf(biodiversities) }

    chipData = when (selectedChip.value) {
        ChipFilter.GEOSITE -> {
            biodiversities.filter {
                it.type != stringResource(R.string.animal) && it.type != stringResource(R.string.plant)
            }
        }

        ChipFilter.ANIMAL -> {
            biodiversities.filter { it.type == stringResource(R.string.animal) }
        }

        ChipFilter.PLANT -> {
            biodiversities.filter { it.type == stringResource(R.string.plant) }
        }

        else -> {
            biodiversities
        }
    }

    HomeScreenContent(
        name = name.value?.getFirstName() ?: "No Name",
        geosites = DummyData.getAllGeosites(),
        biodiversities = chipData,
        selectedChip = selectedChip,
        intentToProfile = { ProfileActivity.start(activity) },
        intentToSearchResult = { SearchActivity.start(activity) },
        intentToGeoprogramme = { GeoprogrammeActivity.start(activity) },
        intentToGeosite = { GeositesActivity.start(activity) }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    name: String = "",
    geosites: List<Geosite> = emptyList(),
    biodiversities: List<Biodiversity> = emptyList(),
    selectedChip: MutableState<ChipFilter> = remember { mutableStateOf(ChipFilter.ALL) },
    intentToProfile: () -> Unit = {},
    intentToSearchResult: () -> Unit = {},
    intentToGeoprogramme: () -> Unit = {},
    intentToGeosite: () -> Unit = {},
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = Dimension.SIZE_8)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_4),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .height(Dimension.SIZE_50)
                        .weight(1f)
                        .padding(horizontal = Dimension.SIZE_12)
                        .clickable(
                            onClick = intentToSearchResult,
                            interactionSource = interactionSource,
                            indication = null,
                        ),
                    value = "",
                    onValueChange = {},
                    shape = MaterialTheme.shapes.small,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search_hint),
                            color = Color.Black.copy(alpha = 0.4f),
                        )
                    },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.clickable(
                                onClick = intentToSearchResult,
                                interactionSource = interactionSource,
                                indication = null,
                            ),
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null
                        )
                    },
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Black.copy(alpha = 0.6f),
                        disabledBorderColor = Color.Black.copy(alpha = 0.6f),
                        cursorColor = Color.Transparent
                    ),
                )
                Icon(
                    modifier = Modifier
                        .padding(end = Dimension.SIZE_24)
                        .clickable(
                            onClick = intentToProfile
                        ),
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = Dimension.SIZE_18)
            ) {
                Text(
                    modifier = Modifier.padding(top = Dimension.SIZE_12),
                    text = stringResource(id = R.string.welcome),
                    style = typography.h3,
                    color = md_theme_dark_secondary
                )
                Text(
                    text = stringResource(R.string.geopark_belitong_user, name),
                    style = typography.h1,
                    color = Color.Black
                )
                HomeCarouselView(
                    geosites = geosites,
                    onItemClicked = {
                        context.getString(R.string.on_click_handler).showToast(context)
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Dimension.SIZE_24),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Dimension.SIZE_10)
                ) {
                    ButtonWithDrawableTop(
                        modifier = Modifier.weight(1f),
                        buttonColor = ButtonDefaults.buttonColors(containerColor = md_theme_dark_tertiary),
                        textButton = stringResource(R.string.geosites),
                        textColor = Color.Black,
                        drawableStart = painterResource(R.drawable.ic_geosites),
                        onClick = intentToGeosite
                    )
                    ButtonWithDrawableTop(
                        modifier = Modifier.weight(1f),
                        buttonColor = ButtonDefaults.buttonColors(containerColor = dark_Quaternary),
                        textButton = stringResource(R.string.geoprogramme),
                        textColor = Color.Black,
                        drawableStart = painterResource(R.drawable.ic_geoprogramme),
                        onClick = intentToGeoprogramme
                    )
                    ButtonWithDrawableTop(
                        modifier = Modifier.weight(1f),
                        buttonColor = ButtonDefaults.buttonColors(containerColor = md_theme_dark_secondary),
                        textButton = stringResource(R.string.maps),
                        textColor = Color.Black,
                        drawableStart = painterResource(R.drawable.ic_maps),
                        onClick = {
                            context.getString(R.string.on_click_handler).showToast(context)
                        }
                    )
                }
                Text(
                    modifier = Modifier.padding(top = Dimension.SIZE_16),
                    text = stringResource(R.string.explore_geopark),
                    style = typography.h3,
                    color = Color.Black
                )
                ChipGroupSingleSelection(
                    modifier = Modifier.fillMaxWidth(),
                    listFilter = getBiodiversityFilter(),
                    selectedChip = selectedChip.value,
                    onChipSelected = { selectedChip.value = it },
                )
                LazyHorizontalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .heightIn(max = 180.dp)
                        .padding(top = Dimension.SIZE_8, bottom = Dimension.SIZE_12),
                    rows = GridCells.Fixed(1),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.spacedBy(Dimension.SIZE_10),
                ) {
                    items(biodiversities) {
                        HomeGridItem(
                            biodiversity = it,
                            onItemClicked = {
                                context.getString(R.string.on_click_handler).showToast(context)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeCarouselView(
    geosites: List<Geosite> = emptyList(),
    onItemClicked: (Geosite) -> Unit = {},
) {
    val carouselPageCount by remember { mutableStateOf(5) }
    val carouselPagerState = rememberPagerState()

    Column {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .height(250.dp)
                .padding(top = Dimension.SIZE_8, bottom = Dimension.SIZE_18),
            pageCount = carouselPageCount,
            state = carouselPagerState,
            pageSpacing = Dimension.SIZE_12,
        ) { page ->
            CarouselPagerItem(
                page = page, geosites = geosites, onItemClicked = onItemClicked
            )
        }
        DotsIndicator(
            dotCount = carouselPageCount,
            type = ShiftIndicatorType(
                dotsGraphic = DotGraphic(
                    color = seed,
                    size = Dimension.SIZE_12,
                ), shiftSizeFactor = 2.5f
            ),
            pagerState = carouselPagerState,
            dotSpacing = Dimension.SIZE_6,
        )
    }
}
