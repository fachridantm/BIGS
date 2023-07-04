package id.belitong.bigs.ui.components

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat

@Composable
fun HideSystemUIBars() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val window = (context as? Activity)?.window
            val windowInsetsController = window?.insetsController
            windowInsetsController?.apply {
                hide(
                    WindowInsets.Type.statusBars()
                            or WindowInsets.Type.navigationBars()
                )
                systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            (context as? Activity)?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        onDispose {
            // Revert the changes when the composable is disposed
            (context as? Activity)?.window?.let { WindowCompat.setDecorFitsSystemWindows(it, true) }
        }
    }
}