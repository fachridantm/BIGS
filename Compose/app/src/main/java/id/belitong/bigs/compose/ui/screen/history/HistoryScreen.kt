package id.belitong.bigs.compose.ui.screen.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.Order
import id.belitong.bigs.compose.core.utils.DummyData.getAllOrder
import id.belitong.bigs.compose.core.utils.showToast
import id.belitong.bigs.compose.ui.composable.components.HistoryGridItem
import id.belitong.bigs.compose.ui.composable.components.TabContent
import id.belitong.bigs.compose.ui.composable.components.TabLayout
import id.belitong.bigs.compose.ui.composable.utils.getActivity
import id.belitong.bigs.compose.ui.navigation.MainNavGraph
import id.belitong.bigs.compose.ui.screen.profile.ProfileActivity
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.typography

@OptIn(ExperimentalPagerApi::class)
@MainNavGraph
@Destination
@Composable
fun HistoryScreen(
    navigator: DestinationsNavigator? = null
) {
    val activity = getActivity()

    val tabData = getAllOrder()
    val tabList = listOf(stringResource(R.string.my_order), stringResource(R.string.my_report))
    val pagerState = rememberPagerState(pageCount = tabData.size)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimension.SIZE_12),
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = Dimension.SIZE_48),
                text = stringResource(id = R.string.history),
                style = typography.h3,
                color = Color.Black.copy(alpha = 0.6f),
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(end = Dimension.SIZE_24)
                    .clickable(
                        onClick = { ProfileActivity.start(activity) }
                    )
            )
        }
        TabLayout(
            tabList = tabList,
            pagerState = pagerState,
        )
        TabContent(content = { HistoryScreenContent(orders = tabData) }, pagerState = pagerState)
    }
}

@Composable
fun HistoryScreenContent(
    modifier: Modifier = Modifier,
    orders: List<Order> = emptyList()
) {
    val context = LocalContext.current

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(Dimension.SIZE_12),
        horizontalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(Dimension.SIZE_12)
    ) {
        items(orders) {
            HistoryGridItem(
                order = it,
                onItemClicked = { context.getString(R.string.on_click_handler).showToast(context) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen()
}
