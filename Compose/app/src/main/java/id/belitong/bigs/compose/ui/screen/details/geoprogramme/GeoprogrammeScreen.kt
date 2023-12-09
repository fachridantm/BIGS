package id.belitong.bigs.compose.ui.screen.details.geoprogramme

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.ui.composable.components.ButtonWithDrawableStart
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun GeoprogrammeScreen(
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    val context = LocalContext.current
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    GeoprogrammeScreenContent(
        modifier = modifier,
        onBackPressed = { onBackPressedDispatcher?.onBackPressed() },
        onClickTourGuide = {
            scope.launch {
                snackbarHostState.showSnackbar(
                    context.getString(R.string.on_click_handler)
                )
            }
        },
        onClickCommunity = {
            scope.launch {
                snackbarHostState.showSnackbar(
                    context.getString(R.string.on_click_handler)
                )
            }
        },
        onClickLocalResident = {
            scope.launch {
                snackbarHostState.showSnackbar(
                    context.getString(R.string.on_click_handler)
                )
            }
        },
    )
}

@Composable
fun GeoprogrammeScreenContent(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onClickTourGuide: () -> Unit = {},
    onClickCommunity: () -> Unit = {},
    onClickLocalResident: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimension.SIZE_16)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_16, bottom = Dimension.SIZE_26),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .clickable(
                        onClick = onBackPressed
                    ),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(R.string.back),
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.title_activity_geoprogramme),
                style = typography.h3,
                color = Color.Black.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
            )
        }
        ButtonWithDrawableStart(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(top = Dimension.SIZE_18),
            buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
            textButton = stringResource(R.string.tour_guide),
            textColor = Color.Black,
            borderStroke = BorderStroke(Dimension.SIZE_1, Color.Black.copy(alpha = 0.4f)),
            drawableImageStart = painterResource(R.drawable.img_tour_guide),
            onClick = onClickTourGuide
        )
        ButtonWithDrawableStart(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(top = Dimension.SIZE_18),
            buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
            textButton = stringResource(R.string.community),
            textColor = Color.Black,
            borderStroke = BorderStroke(Dimension.SIZE_1, Color.Black.copy(alpha = 0.4f)),
            drawableImageStart = painterResource(R.drawable.img_community),
            onClick = onClickCommunity
        )
        ButtonWithDrawableStart(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(top = Dimension.SIZE_18),
            buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
            textButton = stringResource(R.string.local_resident),
            textColor = Color.Black,
            borderStroke = BorderStroke(Dimension.SIZE_1, Color.Black.copy(alpha = 0.4f)),
            drawableImageStart = painterResource(R.drawable.img_local_resident),
            onClick = onClickLocalResident
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GeoprogrammeScreenPreview() {
    GeoprogrammeScreenContent()
}
