package id.belitong.bigs.compose.ui.screen.profile

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.User
import id.belitong.bigs.compose.core.utils.showToast
import id.belitong.bigs.compose.ui.composable.components.ButtonWithDrawableStartAndEnd
import id.belitong.bigs.compose.ui.composable.components.LogoutDialog
import id.belitong.bigs.compose.ui.composable.utils.getActivity
import id.belitong.bigs.compose.ui.screen.auth.AuthActivity
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.md_theme_light_error
import id.belitong.bigs.compose.ui.theme.typography

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val activity = getActivity()
    val context = LocalContext.current
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    var showDialog by remember { mutableStateOf(false) }
    var isLogout by remember { mutableStateOf(false) }

    val name = profileViewModel.getName().observeAsState()

    ProfileScreenContent(
        user = User(
            userId = "2",
            name = name.value ?: "No Name",
        ),
        onBackPressed = { onBackPressedDispatcher?.onBackPressed() },
        onClickEditProfile = { context.getString(R.string.on_click_handler).showToast(context) },
        onClickLanguage = { context.getString(R.string.on_click_handler).showToast(context) },
        onClickHelp = { context.getString(R.string.on_click_handler).showToast(context) },
        onClickAboutUs = { context.getString(R.string.on_click_handler).showToast(context) },
        onClickLogout = {
            showDialog = true
        },
    )
    if (showDialog) {
        LogoutDialog(
            onClickCancel = {
                showDialog = false
            },
            onClickLogout = {
                isLogout = true
            },
        )
    }
    LaunchedEffect(isLogout) {
        if (isLogout) {
            isLogout = false
            AuthActivity.startNewTask(activity)
            activity.finish()
            profileViewModel.deleteSession()
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    user: User,
    onBackPressed: () -> Unit = {},
    onClickEditProfile: () -> Unit = {},
    onClickLanguage: () -> Unit = {},
    onClickHelp: () -> Unit = {},
    onClickAboutUs: () -> Unit = {},
    onClickLogout: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimension.SIZE_16)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_16),
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
                text = stringResource(id = R.string.title_activity_profile),
                style = typography.h3,
                color = Color.Black.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,

                )
        }
        GlideImage(
            model = user.photoUrl,
            contentDescription = stringResource(R.string.photo_profile),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = Dimension.SIZE_36)
                .width(120.dp)
                .height(120.dp)
                .clip(CircleShape),
        ) {
            it.placeholder(R.drawable.ic_profile)
        }
        Text(
            modifier = Modifier.padding(top = Dimension.SIZE_24),
            text = user.name,
            style = typography.h4,
            color = Color.Black.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "dummy@gmail.com",
            style = typography.body2,
            color = Color.Black.copy(alpha = 0.4f),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "081318253665",
            style = typography.body2,
            color = Color.Black.copy(alpha = 0.4f),
            textAlign = TextAlign.Center,
        )
        ButtonWithDrawableStartAndEnd(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_68)
                .shadow(
                    elevation = Dimension.SIZE_2,
                    shape = RoundedCornerShape(Dimension.SIZE_10)
                ),
            buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
            textButton = stringResource(R.string.edit_profile),
            textColor = Color.Black,
            textStyle = typography.h3,
            borderStroke = BorderStroke(1.dp, Color.Black),
            drawableStart = painterResource(R.drawable.ic_profile),
            drawableEnd = painterResource(R.drawable.ic_arrow_right),
            onClick = onClickEditProfile
        )
        ButtonWithDrawableStartAndEnd(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_18)
                .shadow(
                    elevation = Dimension.SIZE_2,
                    shape = RoundedCornerShape(Dimension.SIZE_10)
                ),
            buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
            textButton = stringResource(R.string.language),
            textColor = Color.Black,
            textStyle = typography.h3,
            borderStroke = BorderStroke(1.dp, Color.Black),
            drawableStart = painterResource(R.drawable.ic_language),
            drawableEnd = painterResource(R.drawable.ic_arrow_right),
            onClick = onClickLanguage
        )
        ButtonWithDrawableStartAndEnd(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_18)
                .shadow(
                    elevation = Dimension.SIZE_2,
                    shape = RoundedCornerShape(Dimension.SIZE_10)
                ),
            buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
            textButton = stringResource(R.string.help),
            textColor = Color.Black,
            textStyle = typography.h3,
            borderStroke = BorderStroke(1.dp, Color.Black),
            drawableStart = painterResource(R.drawable.ic_help),
            drawableEnd = painterResource(R.drawable.ic_arrow_right),
            onClick = onClickHelp
        )
        ButtonWithDrawableStartAndEnd(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_18)
                .shadow(
                    elevation = Dimension.SIZE_2,
                    shape = RoundedCornerShape(Dimension.SIZE_10)
                ),
            buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
            textButton = stringResource(R.string.about_us),
            textColor = Color.Black,
            textStyle = typography.h3,
            borderStroke = BorderStroke(1.dp, Color.Black),
            drawableStart = painterResource(R.drawable.ic_about),
            drawableEnd = painterResource(R.drawable.ic_arrow_right),
            onClick = onClickAboutUs
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = Dimension.SIZE_48,
                    horizontal = Dimension.SIZE_8,
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = md_theme_light_error,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(Dimension.SIZE_8),
            onClick = onClickLogout
        ) {
            Text(
                text = stringResource(R.string.logout),
                style = typography.h4
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent(
        user = User(
            userId = "1",
            name = "John Doe",
            photoUrl = "https://i.pinimg.com/originals/0f/6a/9e/0f6a9e9e2e2e2e2e2e2e2e2e2e2e2e2.jpg"
        ),
    )
}