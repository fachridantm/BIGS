package id.belitong.bigs.compose.ui.screen.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.compose.ui.screen.NavGraphs
import id.belitong.bigs.compose.ui.theme.BIGSComposeTheme

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BIGSComposeTheme {
                DestinationsNavHost(navGraph = NavGraphs.auth)
            }
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, AuthActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}