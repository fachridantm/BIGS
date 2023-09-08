package id.belitong.bigs.compose.ui.composable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import id.belitong.bigs.compose.ui.navigation.MainBottomNavItem
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.md_theme_dark_primary
import id.belitong.bigs.compose.ui.theme.seed

@Composable
fun MainBottomNavigation(navController: NavController) {
    val items = listOf(
        MainBottomNavItem.Home,
        MainBottomNavItem.Add,
        MainBottomNavItem.History
    )
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = seed,
                shape = RoundedCornerShape(topStart = Dimension.SIZE_12, topEnd = Dimension.SIZE_12)
            )
            .padding(bottom = Dimension.SIZE_12, top = Dimension.SIZE_12),
        contentColor = Color.White,
        backgroundColor = seed,
        elevation = Dimension.SIZE_0
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { index, item ->
            if (index == 3) Spacer(modifier = Modifier.weight(0.8f, true))
            val selectedColor =
                if (currentRoute == item.route) md_theme_dark_primary else Color.Transparent
            BottomNavigationItem(
                label = { Text(text = item.title, color = Color.White) },
                icon = {
                    Icon(
                        modifier = Modifier
                            .padding(bottom = Dimension.SIZE_7)
                            .background(
                                color = selectedColor,
                                shape = CircleShape
                            )
                            .padding(horizontal = Dimension.SIZE_20, vertical = Dimension.SIZE_4),
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = Color.White
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                selectedContentColor = seed
            )
        }
    }
}