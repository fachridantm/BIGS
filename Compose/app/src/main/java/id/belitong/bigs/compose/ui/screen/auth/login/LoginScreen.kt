package id.belitong.bigs.compose.ui.screen.auth.login

import android.app.Activity
import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.User
import id.belitong.bigs.compose.core.utils.showToast
import id.belitong.bigs.compose.ui.composable.components.BasicLottieAnimation
import id.belitong.bigs.compose.ui.composable.components.ButtonWithDrawableStart
import id.belitong.bigs.compose.ui.composable.components.ValidationForm
import id.belitong.bigs.compose.ui.composable.model.FormValidation
import id.belitong.bigs.compose.ui.composable.utils.ComposableObserver
import id.belitong.bigs.compose.ui.composable.utils.getActivity
import id.belitong.bigs.compose.ui.navigation.AuthNavGraph
import id.belitong.bigs.compose.ui.screen.destinations.RegisterScreenDestination
import id.belitong.bigs.compose.ui.screen.main.MainActivity
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.md_theme_dark_secondary
import id.belitong.bigs.compose.ui.theme.seed
import id.belitong.bigs.compose.ui.theme.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AuthNavGraph(true)
@Destination
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigator: DestinationsNavigator? = null,
    scope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val activity = getActivity()

    val loginResult = loginViewModel.result.observeAsState()
    var isLoading by remember { mutableStateOf(false) }
    var hasBeenTriggered by rememberSaveable { mutableStateOf(false) }

    BackHandler {
        activity.finish()
    }

    LoginScreenContent(
        onClick = { email, password ->
            loginViewModel.loginUser(email, password)
        },
        isLoading = isLoading,
        navigator = navigator,
        scope = scope,
        snackbarHostState = snackbarHostState
    )

    ComposableObserver(
        state = loginResult,
        onLoading = {
            isLoading = true
        },
        onSuccess = {
            if (hasBeenTriggered) return@ComposableObserver

            isLoading = false
            val token = it.loginResult?.token
            val user = it.loginResult
            val message = it.message.toString()

            if (token != null && user != null) {
                saveSession(loginViewModel, activity, token, user)
            }

            stringResource(id = R.string.login_result, message).showToast(activity)
            hasBeenTriggered = true
        },
        onError = { message ->
            isLoading = false
            message.showToast(activity)
        }
    )
}

fun saveSession(loginViewModel: LoginViewModel, activity: Activity, token: String, user: User) {
    loginViewModel.saveSession(token, user)
    MainActivity.start(activity)
    activity.finish()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier.semantics { contentDescription = "LoginScreen" },
    context: Context = LocalContext.current,
    onClick: (String, String) -> Unit,
    isLoading: Boolean = false,
    navigator: DestinationsNavigator? = null,
    scope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var emailErrorMessage by remember { mutableStateOf("") }
    var passwordErrorMessage by remember { mutableStateOf("") }

    val interactionSource = remember { MutableInteractionSource() }

    val visibility = if (isLoading) 1f else 0f
    val elevation = if (isLoading) Dimension.SIZE_0 else Dimension.SIZE_2

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimension.SIZE_32)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
        ) {
            val emailValidation =
                remember {
                    mutableStateOf(
                        FormValidation(
                            hint = context.getString(R.string.email_address),
                            errorMessage = emailErrorMessage
                        )
                    )
                }

            val passwordValidation = remember {
                mutableStateOf(
                    FormValidation(
                        hint = context.getString(R.string.password),
                        errorMessage = passwordErrorMessage
                    )
                )
            }

            val email = emailValidation.value.text
            val password = passwordValidation.value.text

            emailErrorMessage = if (email.isEmpty()) {
                stringResource(R.string.email_not_allowed_to_be_empty)
            } else {
                stringResource(R.string.invalid_email_format)
            }

            passwordErrorMessage = if (password.isEmpty()) {
                stringResource(R.string.password_not_allowed_to_be_empty)
            } else {
                stringResource(R.string.minimum_password_length)
            }

            val buttonValidation =
                email.isNotEmpty() &&
                        Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                        password.isNotEmpty() &&
                        password.length >= 8

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier
                        .width(166.dp)
                        .height(253.15.dp),
                    contentDescription = null,
                    painter = painterResource(R.drawable.img_earth)
                )
            }
            Text(
                textAlign = TextAlign.Start,
                text = stringResource(R.string.sign_in),
                style = typography.h2,
            )
            ValidationForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_24)
                    .semantics { contentDescription = "email_login_form" },
                state = emailValidation,
                onFormValueChange = {
                    emailValidation.value = emailValidation.value.copy(
                        text = it
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                onImeKeyAction = {
                    keyboardController?.hide()
                },
            )
            ValidationForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_18)
                    .semantics { contentDescription = "password_login_form" },
                state = passwordValidation,
                isPasswordForm = true,
                onFormValueChange = {
                    passwordValidation.value = passwordValidation.value.copy(
                        text = it
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                onImeKeyAction = {
                    keyboardController?.hide()
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = Dimension.SIZE_8)
                        .clickable(
                            enabled = !isLoading,
                            onClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        context.getString(R.string.on_click_handler)
                                    )
                                }
                            }
                        ),
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.End,
                    text = stringResource(R.string.forgot_password),
                    style = typography.h5
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_30)
                    .semantics { contentDescription = "sign_in_button" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = seed, contentColor = Color.White
                ),
                shape = RoundedCornerShape(Dimension.SIZE_8),
                enabled = !isLoading,
                onClick = {
                    emailValidation.value = emailValidation.value.copy(
                        isError = email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email)
                            .matches(),
                        errorMessage = emailErrorMessage
                    )

                    passwordValidation.value = passwordValidation.value.copy(
                        isError = password.isEmpty() || password.length < 8,
                        errorMessage = passwordErrorMessage
                    )

                    if (buttonValidation) {
                        onClick(email, password)
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.sign_in), style = typography.button
                )
            }
            Row(
                modifier = Modifier.padding(top = Dimension.SIZE_26),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .weight(2f),
                    text = stringResource(R.string.or),
                    style = typography.h4
                )
            }
            ButtonWithDrawableStart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_28)
                    .indication(
                        interactionSource = interactionSource,
                        indication = rememberRipple(
                            color = Color.DarkGray,
                            radius = Dimension.SIZE_18,
                            bounded = false,
                        )
                    ),
                interactionSource = interactionSource,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = elevation,
                    pressedElevation = elevation,
                    disabledElevation = elevation,
                ),
                buttonColor = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.White,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White,
                ),
                textButton = stringResource(R.string.sign_in_with_google),
                textColor = Color.Black,
                borderStroke = BorderStroke(Dimension.SIZE_1, Color.Black),
                shape = RoundedCornerShape(Dimension.SIZE_8),
                drawableStart = painterResource(R.drawable.ic_google),
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            context.getString(R.string.on_click_handler)
                        )
                    }
                },
                enabled = !isLoading
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_100),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.dont_have_an_account),
                    color = Color.Black,
                    style = typography.body1
                )
                Text(
                    modifier = Modifier
                        .padding(start = Dimension.SIZE_4)
                        .clickable(
                            enabled = !isLoading,
                            onClick = {
                                navigator?.navigate(RegisterScreenDestination)
                            }
                        ),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.sign_up),
                    color = md_theme_dark_secondary,
                    style = typography.body1
                )
            }
        }
        BasicLottieAnimation(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
                .alpha(visibility),
            resId = R.raw.loading,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        onClick = { email, password ->
            Log.d("LoginScreen", "email: $email, password: $password")
        }
    )
}