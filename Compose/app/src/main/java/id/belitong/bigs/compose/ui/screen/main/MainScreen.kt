package id.belitong.bigs.compose.ui.screen.main

import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.rememberNavHostEngine
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.utils.NOTIFICATION_PERMISSION
import id.belitong.bigs.compose.core.utils.showToast
import id.belitong.bigs.compose.ui.composable.components.MainBottomNavigation
import id.belitong.bigs.compose.ui.screen.NavGraphs
import id.belitong.bigs.compose.ui.screen.add.AddScreen
import id.belitong.bigs.compose.ui.screen.destinations.AddScreenDestination
import id.belitong.bigs.compose.ui.screen.destinations.HistoryScreenDestination
import id.belitong.bigs.compose.ui.screen.destinations.HomeScreenDestination
import id.belitong.bigs.compose.ui.screen.history.HistoryScreen
import id.belitong.bigs.compose.ui.screen.home.HomeScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator? = null
) {
    val context = LocalContext.current
    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val notificationPermission = NOTIFICATION_PERMISSION
    val requiredPermissions = arrayOf(notificationPermission)
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // PERMISSION GRANTED
            requiredPermissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
        } else {
            // PERMISSION NOT GRANTED
            context.getString(R.string.not_given_access).showToast(context)
        }
    }

    SideEffect {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(notificationPermission)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MainBottomNavigation(navController) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {
        Box(
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        ) {
            DestinationsNavHost(
                navGraph = NavGraphs.main,
                navController = navController,
            ) {
                composable(HomeScreenDestination) {
                    HomeScreen(navigator, scope = scope, snackbarHostState = snackbarHostState)
                }
                composable(AddScreenDestination) {
                    AddScreen(navigator, scope = scope, snackbarHostState = snackbarHostState)
                }
                composable(HistoryScreenDestination) {
                    HistoryScreen(
                        navigator = navigator,
                        scope = scope,
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }
    }
}