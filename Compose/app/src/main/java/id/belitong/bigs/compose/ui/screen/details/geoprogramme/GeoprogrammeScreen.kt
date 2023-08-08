package id.belitong.bigs.compose.ui.screen.details.geoprogramme

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import id.belitong.bigs.compose.ui.composable.utils.getActivity

@Composable
fun GeoprogrammeScreen(

) {
    val activity = getActivity()

    BackHandler {
        activity.onNavigateUp()
    }

    GeoprogrammeScreenContent()
}

@Composable
fun GeoprogrammeScreenContent() {
    // TODO: Create UI GeoprogrammeScreenContent
}
