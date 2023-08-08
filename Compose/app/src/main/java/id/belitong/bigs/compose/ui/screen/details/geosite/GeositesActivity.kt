package id.belitong.bigs.compose.ui.screen.details.geosite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.compose.ui.theme.BIGSComposeTheme

@AndroidEntryPoint
class GeositesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BIGSComposeTheme {
                GeositesScreen()
            }
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, GeositesActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}