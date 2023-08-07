package id.belitong.bigs.compose.ui.screen.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.ui.composable.components.ButtonWithDrawableStart
import id.belitong.bigs.compose.ui.navigation.MainNavGraph
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.md_theme_light_primary
import id.belitong.bigs.compose.ui.theme.typography

@MainNavGraph
@Destination
@Composable
fun AddScreen(
    navigator: DestinationsNavigator? = null
) {
    AddScreenContent()
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AddScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimension.SIZE_24, vertical = Dimension.SIZE_12),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = Dimension.SIZE_8),
            text = stringResource(id = R.string.set_up_your_camera),
            style = typography.h3,
            color = Color.Black.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(vertical = Dimension.SIZE_64),
            text = stringResource(R.string.scan_animal),
            style = typography.h4,
            textAlign = TextAlign.Center
        )
        Image(
            modifier = Modifier.padding(bottom = Dimension.SIZE_50),
            painter = painterResource(id = R.drawable.ic_plant_scanning),
            contentDescription = stringResource(R.string.scanning_plant),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = Dimension.SIZE_48),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            ButtonWithDrawableStart(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f)
                    .padding(end = Dimension.SIZE_8),
                buttonColor = ButtonDefaults.buttonColors(
                    containerColor = md_theme_light_primary,
                    contentColor = Color.White
                ),
                textButton = stringResource(id = R.string.take_photo),
                textColor = Color.White,
                drawableStart = painterResource(id = R.drawable.ic_camera),
                shape = RoundedCornerShape(Dimension.SIZE_12),
                innerPadding = PaddingValues(vertical = Dimension.SIZE_10),
                iconPadding = PaddingValues(horizontal = Dimension.SIZE_4),
                textPadding = PaddingValues(Dimension.SIZE_0),
                onClick = {}
            )
            ButtonWithDrawableStart(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f)
                    .padding(start = Dimension.SIZE_8),
                buttonColor = ButtonDefaults.buttonColors(
                    containerColor = md_theme_light_primary,
                    contentColor = Color.White
                ),
                textButton = stringResource(id = R.string.upload_image),
                textColor = Color.White,
                drawableStart = painterResource(id = R.drawable.ic_upload),
                shape = RoundedCornerShape(Dimension.SIZE_12),
                innerPadding = PaddingValues(vertical = Dimension.SIZE_10),
                iconPadding = PaddingValues(horizontal = Dimension.SIZE_4),
                textPadding = PaddingValues(Dimension.SIZE_0),
                onClick = {}
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = Dimension.SIZE_24),
            colors = ButtonDefaults.buttonColors(
                containerColor = md_theme_light_primary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(Dimension.SIZE_12),
            contentPadding = PaddingValues(vertical = Dimension.SIZE_12),
            onClick = {},
        ) {
            Text(
                text = stringResource(id = R.string.scan_photo),
                style = typography.button,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddScreenPreview() {
    AddScreenContent()
}