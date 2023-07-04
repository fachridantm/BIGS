package id.belitong.bigs.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    onItemClick: () -> Unit
) {
    BottomNavigation(
        modifier = modifier
    ) {

    }
}