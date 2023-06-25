package id.belitong.bigs.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import id.belitong.bigs.ui.navigation.BigsNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (bottomBarState.value) {

            }
        }
    ) { innerPadding ->
        BigsNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            isBottomBarVisible = { bottomBarState.value = it }
        )
    }
}