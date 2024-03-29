package id.belitong.bigs.compose.ui.screen.add

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.belitong.bigs.compose.BuildConfig
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.Plant
import id.belitong.bigs.compose.core.utils.CAMERA_PERMISSION
import id.belitong.bigs.compose.core.utils.EXTERNAL_STORAGE_PERMISSION
import id.belitong.bigs.compose.core.utils.MEDIA_PERMISSION
import id.belitong.bigs.compose.core.utils.createTempFile
import id.belitong.bigs.compose.core.utils.rotateBitmap
import id.belitong.bigs.compose.core.utils.showToast
import id.belitong.bigs.compose.core.utils.uriToFile
import id.belitong.bigs.compose.ui.composable.components.BasicLottieAnimation
import id.belitong.bigs.compose.ui.composable.components.ButtonWithDrawableStart
import id.belitong.bigs.compose.ui.composable.components.ScanResultDialog
import id.belitong.bigs.compose.ui.composable.utils.ComposableObserver
import id.belitong.bigs.compose.ui.composable.utils.getActivity
import id.belitong.bigs.compose.ui.navigation.MainNavGraph
import id.belitong.bigs.compose.ui.screen.main.MainViewModel
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.md_theme_light_primary
import id.belitong.bigs.compose.ui.theme.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

@MainNavGraph
@Destination
@Composable
fun AddScreen(
    navigator: DestinationsNavigator? = null,
    mainViewModel: MainViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val context = LocalContext.current
    val activity = getActivity()

    val plantState = mainViewModel.plant.observeAsState()

    var isLoading by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    var isFailed by remember { mutableStateOf(false) }

    val plant = remember { mutableStateOf<Plant?>(null) }

    var showDialog by remember { mutableStateOf(false) }

    var getFile by remember { mutableStateOf<File?>(null) }
    var image by remember { mutableStateOf<Any?>(null) }
    var currentPhotoPath by remember { mutableStateOf("") }

    val cameraPermission = CAMERA_PERMISSION
    val mediaPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        MEDIA_PERMISSION
    } else {
        EXTERNAL_STORAGE_PERMISSION
    }

    val requiredPermissions = arrayOf(cameraPermission, mediaPermission)
    val requestMultiplePermissionsLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach {
            if (!it.value) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        CAMERA_PERMISSION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    if (it.key == cameraPermission) {
                        context.getString(R.string.access_not_granted, "Camera").showToast(context)
                    }
                } else {
                    context.getString(R.string.access_not_granted, "Media").showToast(context)
                }
            }
        }
    }

    ComposableObserver(
        state = plantState,
        onLoading = { isLoading = true },
        onSuccess = {
            isLoading = false
            plant.value = it
        },
        onError = { message ->
            isLoading = false
            message.showToast(activity)
        }
    )

    SideEffect {
        requestMultiplePermissionsLauncher.launch(requiredPermissions)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val myFile = File(currentPhotoPath)

                val result = rotateBitmap(
                    BitmapFactory.decodeFile(myFile.path), true
                )

                getFile = myFile
                image = result
            }
        }

    val mediaLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val selectedImg: Uri = it.data?.data as Uri
                val myFile = uriToFile(selectedImg, context)

                getFile = myFile
                image = selectedImg
            }
        }

    AddScreenContent(
        image = image,
        cameraHandler = {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(activity.packageManager)
            }

            createTempFile(context).also { file ->
                val photoURI: Uri = FileProvider.getUriForFile(
                    context,
                    BuildConfig.APPLICATION_ID,
                    file
                )
                currentPhotoPath = file.absolutePath

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                cameraLauncher.launch(intent)
            }

            isSuccess = false
            isFailed = true
        },
        mediaHandler = {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = context.getString(R.string.image_format)

            val chooser = Intent.createChooser(intent, context.getString(R.string.pilih_gambar))
            mediaLauncher.launch(chooser)

            isSuccess = true
            isFailed = false
        },
        scanHandler = {
            if (getFile != null) {
                scope.launch {
                    mainViewModel.getPlant()
                    delay(2000)
                    isLoading = false
                    showDialog = true
                }
            } else {
                context.getString(R.string.no_image_selected).showToast(context)
            }
        },
        isLoading = isLoading,
    )

    if (showDialog) {
        ScanResultDialog(
            plant = plant.value,
            isFailed = isFailed,
            isSuccess = isSuccess,
            onClickDetails = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        context.getString(R.string.on_click_handler)
                    )
                }
            },
            onClickCancel = { showDialog = false },
            onClickAddData = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        context.getString(R.string.on_click_handler)
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AddScreenContent(
    modifier: Modifier = Modifier,
    image: Any? = null,
    cameraHandler: () -> Unit = {},
    mediaHandler: () -> Unit = {},
    scanHandler: () -> Unit = {},
    isLoading: Boolean = false,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        val visibility = if (isLoading) 1f else 0f
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = Dimension.SIZE_24, vertical = Dimension.SIZE_12),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(top = Dimension.SIZE_8),
                text = stringResource(id = R.string.set_up_your_camera),
                style = typography.h3,
                color = Color.Black.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(top = Dimension.SIZE_64, bottom = Dimension.SIZE_60),
                text = stringResource(R.string.scan_animal),
                style = typography.h4,
                textAlign = TextAlign.Center
            )
            GlideImage(
                modifier = Modifier.size(220.dp),
                model = image,
                contentDescription = stringResource(R.string.scanning_plant),
                contentScale = ContentScale.Crop
            ) {
                it.placeholder(R.drawable.ic_plant_scanning)
            }
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = Dimension.SIZE_64),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ButtonWithDrawableStart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(0.8f)
                        .padding(end = Dimension.SIZE_8),
                    buttonColor = ButtonDefaults.buttonColors(
                        containerColor = md_theme_light_primary,
                        contentColor = Color.White
                    ),
                    textButton = stringResource(id = R.string.take_photo),
                    textColor = Color.White,
                    drawableStart = painterResource(id = R.drawable.ic_camera),
                    shape = RoundedCornerShape(Dimension.SIZE_12),
                    enabled = !isLoading,
                    innerPadding = PaddingValues(vertical = Dimension.SIZE_12),
                    iconPadding = PaddingValues(horizontal = Dimension.SIZE_4),
                    textPadding = PaddingValues(Dimension.SIZE_0),
                    onClick = cameraHandler
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
                    enabled = !isLoading,
                    innerPadding = PaddingValues(vertical = Dimension.SIZE_12),
                    iconPadding = PaddingValues(horizontal = Dimension.SIZE_4),
                    textPadding = PaddingValues(Dimension.SIZE_0),
                    onClick = mediaHandler
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = Dimension.SIZE_24),
                colors = ButtonDefaults.buttonColors(
                    containerColor = md_theme_light_primary, contentColor = Color.White
                ),
                shape = RoundedCornerShape(Dimension.SIZE_12),
                contentPadding = PaddingValues(vertical = Dimension.SIZE_12),
                enabled = !isLoading,
                onClick = scanHandler
            ) {
                Text(
                    text = stringResource(id = R.string.scan_photo),
                    style = typography.button,
                    color = Color.White
                )
            }
        }
        BasicLottieAnimation(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 157.dp)
                .alpha(visibility),
            resId = R.raw.plant_scanning
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddScreenPreview() {
    AddScreenContent()
}