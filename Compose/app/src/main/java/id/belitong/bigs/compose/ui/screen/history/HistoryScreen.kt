package id.belitong.bigs.compose.ui.screen.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.Order
import id.belitong.bigs.compose.core.domain.model.Report
import id.belitong.bigs.compose.core.utils.showToast
import id.belitong.bigs.compose.ui.composable.components.BasicLottieAnimation
import id.belitong.bigs.compose.ui.composable.components.OrderGridItem
import id.belitong.bigs.compose.ui.composable.components.ReportGridItem
import id.belitong.bigs.compose.ui.composable.components.TabContent
import id.belitong.bigs.compose.ui.composable.components.TabLayout
import id.belitong.bigs.compose.ui.composable.utils.ComposableObserver
import id.belitong.bigs.compose.ui.composable.utils.getActivity
import id.belitong.bigs.compose.ui.navigation.MainNavGraph
import id.belitong.bigs.compose.ui.screen.profile.ProfileActivity
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@MainNavGraph
@Destination
@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel = hiltViewModel(),
    navigator: DestinationsNavigator? = null,
    scope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val activity = getActivity()

    val ordersState = historyViewModel.orders.observeAsState()
    val reportsState = historyViewModel.reports.observeAsState()

    val isLoading = remember { mutableStateOf(false) }

    val orders = remember { mutableStateOf(emptyList<Order>()) }
    val reports = remember { mutableStateOf(emptyList<Report>()) }

    LaunchedEffect(key1 = Unit) {
        historyViewModel.getOrders()
        historyViewModel.getReports()
    }

    ComposableObserver(
        state = ordersState,
        onLoading = {
            isLoading.value = true
        },
        onSuccess = {
            isLoading.value = false
            orders.value = it
        },
        onError = { message ->
            isLoading.value = false
            message.showToast(activity)
        }
    )

    ComposableObserver(
        state = reportsState,
        onLoading = {
            isLoading.value = true
        },
        onSuccess = {
            isLoading.value = false
            reports.value = it
        },
        onError = { message ->
            isLoading.value = false
            message.showToast(activity)
        }
    )

    val tabList = listOf(stringResource(R.string.my_order), stringResource(R.string.my_report))
    val pagerState = rememberPagerState(pageCount = tabList.size)


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

        when (tabList[pagerState.currentPage]) {
            stringResource(R.string.my_order) -> {
                TabContent(
                    content = {
                        OrderScreenContent(
                            orders = orders.value,
                            isLoading = isLoading.value,
                            scope = scope,
                            snackbarHostState = snackbarHostState
                        )
                    },
                    pagerState = pagerState
                )
            }

            stringResource(R.string.my_report) -> {
                TabContent(
                    content = {
                        ReportScreenContent(
                            reports = reports.value,
                            isLoading = isLoading.value,
                            scope = scope,
                            snackbarHostState = snackbarHostState
                        )
                    },
                    pagerState = pagerState
                )
            }
        }
    }
}

@Composable
fun OrderScreenContent(
    modifier: Modifier = Modifier,
    orders: List<Order> = emptyList(),
    isLoading: Boolean = false,
    scope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val context = LocalContext.current
    val visibility = if (isLoading) 1f else 0f

    Box {
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(Dimension.SIZE_12),
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(Dimension.SIZE_12)
        ) {
            items(orders) {
                OrderGridItem(
                    order = it,
                    onItemClicked = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                context.getString(R.string.on_click_handler)
                            )
                        }
                    }
                )
            }
        }
        BasicLottieAnimation(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
                .alpha(visibility),
            resId = R.raw.loading,
        )
    }
}

@Composable
fun ReportScreenContent(
    modifier: Modifier = Modifier,
    reports: List<Report> = emptyList(),
    isLoading: Boolean = false,
    scope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val context = LocalContext.current
    val visibility = if (isLoading) 1f else 0f

    Box {
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(Dimension.SIZE_12),
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(Dimension.SIZE_12)
        ) {
            items(reports) {
                ReportGridItem(
                    report = it,
                    onItemClicked = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                context.getString(R.string.on_click_handler)
                            )
                        }
                    }
                )
            }
        }
        BasicLottieAnimation(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
                .alpha(visibility),
            resId = R.raw.loading,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen()
}
