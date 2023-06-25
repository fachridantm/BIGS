package id.belitong.bigs.ui.navigation

import androidx.annotation.DrawableRes

sealed class Screens(
    val route: String,
    @DrawableRes val icon: Int,
    val title: String,
) {
    object SplashScreen : Screens(
        route = "splashScreen",
        icon = 0,
        title = "Splash Screen"
    )

    object LoginScreen : Screens(
        route = "loginScreen",
        icon = 0,
        title = "Login Screen"
    )

    object HomeScreen : Screens(
        route = "homeScreen",
        icon = 0,
        title = "Home Screen"
    )
}