package id.belitong.bigs.compose.ui.screen.details.geosite

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import id.belitong.bigs.compose.ui.composable.utils.getActivity

@Composable
fun GeositesScreen(

) {
    val activity = getActivity()

    BackHandler {
        activity.onNavigateUp()
    }

    GeositesScreenContent()
}

@Composable
fun GeositesScreenContent() {
    // TODO: Create UI GeositesScreenContent
}
