package id.belitong.bigs.compose.ui.screen.search

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import id.belitong.bigs.compose.ui.composable.utils.getActivity

@Composable
fun SearchScreen(

) {
    val activity = getActivity()

    BackHandler {
        activity.onNavigateUp()
    }

    SearchScreenContent()
}

@Composable
fun SearchScreenContent() {
    // TODO: Create UI Search Screen
}
