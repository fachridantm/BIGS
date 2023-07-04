package id.belitong.bigs.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.ui.theme.BIGSComposeTheme
import id.belitong.bigs.ui.theme.md_theme_light_background

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BIGSComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = md_theme_light_background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
