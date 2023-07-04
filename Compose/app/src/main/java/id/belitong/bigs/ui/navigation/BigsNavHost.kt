package id.belitong.bigs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.belitong.bigs.ui.screen.auth.login.LoginScreen
import id.belitong.bigs.ui.screen.auth.login.LoginViewModel
import id.belitong.bigs.ui.screen.splash.SplashScreen

@Composable
fun BigsNavHost(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    isBottomBarVisible: (Boolean) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route,
        modifier = modifier
    ) {
        composable(Screens.SplashScreen.route) {
            isBottomBarVisible(false)
            SplashScreen(
                onClickToNavigate = {
                    if (loginViewModel.token.isNotEmpty()) {
                        navController.popBackStack()
                        navController.navigate(Screens.HomeScreen.route)
                    } else {
                        navController.popBackStack()
                        navController.navigate(Screens.LoginScreen.route)
                    }
                }
            )
        }
        composable(Screens.LoginScreen.route) {
            isBottomBarVisible(false)
            LoginScreen(
                onClickLogin = {
                    navController.popBackStack()
                    navController.navigate(Screens.HomeScreen.route)
                }
            )
        }
    }
}