package id.belitong.bigs.compose.ui.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.compose.ui.theme.BIGSComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BIGSComposeTheme {
                MainScreen()
            }
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, MainActivity::class.java).run { context.startActivity(this) }
        }
    }
}
