package id.belitong.bigs.compose.ui.screen.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.compose.ui.theme.BIGSComposeTheme

@AndroidEntryPoint
class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BIGSComposeTheme {
                ProfileScreen()
            }
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, ProfileActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}