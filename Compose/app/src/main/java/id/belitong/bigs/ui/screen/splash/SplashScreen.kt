package id.belitong.bigs.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import id.belitong.bigs.R
import id.belitong.bigs.ui.components.ButtonWithDrawableEnd
import id.belitong.bigs.ui.theme.md_theme_dark_secondary
import id.belitong.bigs.ui.theme.typography

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onClickToNavigate: () -> Unit,
) {
    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isSystemBarsVisible = false

    SplashScreenContent(
        modifier = modifier,
        onClickToNavigate = onClickToNavigate,
    )
}

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
    onClickToNavigate: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.img_screen),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.img_bigs),
                contentDescription = stringResource(id = R.string.app_name),
            )

            Text(
                modifier = Modifier.padding(top = 26.dp),
                text = stringResource(R.string.BIGS),
                textAlign = TextAlign.Center,
                color = Color.White,
                style = typography.h1,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonWithDrawableEnd(
                buttonColor = ButtonDefaults.buttonColors(containerColor = md_theme_dark_secondary),
                textButton = stringResource(R.string.explore_now),
                textColor = Color.Black,
                drawableEnd = painterResource(R.drawable.ic_explore_now),
                drawableTint = Color.Black,
                onClick = onClickToNavigate,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreenContent(
        onClickToNavigate = {}
    )
}