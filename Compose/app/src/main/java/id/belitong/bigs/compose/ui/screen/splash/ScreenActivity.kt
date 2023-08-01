package id.belitong.bigs.compose.ui.screen.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.compose.ui.theme.BIGSComposeTheme

@AndroidEntryPoint
class ScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BIGSComposeTheme {
                SplashScreen()
            }
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, ScreenActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}