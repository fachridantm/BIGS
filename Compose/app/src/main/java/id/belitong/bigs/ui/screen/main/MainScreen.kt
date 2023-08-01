package id.belitong.bigs.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.rememberNavHostEngine
import id.belitong.bigs.ui.composable.components.MainBottomNavigation
import id.belitong.bigs.ui.screen.NavGraphs
import id.belitong.bigs.ui.screen.add.AddScreen
import id.belitong.bigs.ui.screen.destinations.AddScreenDestination
import id.belitong.bigs.ui.screen.destinations.HistoryScreenDestination
import id.belitong.bigs.ui.screen.destinations.HomeScreenDestination
import id.belitong.bigs.ui.screen.history.HistoryScreen
import id.belitong.bigs.ui.screen.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator? = null
) {
    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MainBottomNavigation(navController) }
    ) {
        Box(
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        ) {
            DestinationsNavHost(
                navGraph = NavGraphs.main,
                navController = navController,
            ) {
                composable(HomeScreenDestination) {
                    HomeScreen(navigator)
                }
                composable(AddScreenDestination) {
                    AddScreen(navigator)
                }
                composable(HistoryScreenDestination) {
                    HistoryScreen(navigator)
                }
            }
        }
    }
}