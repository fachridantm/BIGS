package id.belitong.bigs.ui.navigation

import id.belitong.bigs.R
import id.belitong.bigs.ui.screen.destinations.AddScreenDestination
import id.belitong.bigs.ui.screen.destinations.HistoryScreenDestination
import id.belitong.bigs.ui.screen.destinations.HomeScreenDestination

sealed class MainBottomNavItem(val title : String, val icon : Int, val route : String){
    object Home : MainBottomNavItem("Home", R.drawable.ic_home, HomeScreenDestination.route)
    object Add: MainBottomNavItem("Scan & Add", R.drawable.ic_add, AddScreenDestination.route)
    object History: MainBottomNavItem("History", R.drawable.ic_history, HistoryScreenDestination.route)
}
