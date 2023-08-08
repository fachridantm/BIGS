package id.belitong.bigs.compose.ui.screen.profile

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import id.belitong.bigs.compose.ui.composable.utils.getActivity

@Composable
fun ProfileScreen(

) {
    val activity = getActivity()

    BackHandler {
        activity.onNavigateUp()
    }

}