package id.belitong.bigs.ui.screen.auth.login

import android.app.Activity
import android.util.Log
import android.util.Patterns
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.belitong.bigs.R
import id.belitong.bigs.core.domain.model.FormValidation
import id.belitong.bigs.core.domain.model.User
import id.belitong.bigs.core.utils.showToast
import id.belitong.bigs.ui.composable.components.ButtonWithDrawableStart
import id.belitong.bigs.ui.composable.components.ValidationForm
import id.belitong.bigs.ui.composable.utils.ComposableObserver
import id.belitong.bigs.ui.composable.utils.getActivity
import id.belitong.bigs.ui.composable.utils.getContext
import id.belitong.bigs.ui.navigation.AuthNavGraph
import id.belitong.bigs.ui.screen.destinations.RegisterScreenDestination
import id.belitong.bigs.ui.screen.main.MainActivity
import id.belitong.bigs.ui.theme.Dimension
import id.belitong.bigs.ui.theme.md_theme_dark_secondary
import id.belitong.bigs.ui.theme.seed
import id.belitong.bigs.ui.theme.typography

@AuthNavGraph(true)
@Destination
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigator: DestinationsNavigator? = null,
) {
    val activity = getActivity()

    val loginResult = loginViewModel.result.observeAsState()
    val isLoading = remember { mutableStateOf(false) }

    BackHandler {
        activity.finish()
    }

    LoginScreenContent(
        onClick = { email, password ->
            loginViewModel.loginUser(email, password)
        },
        isLoading = isLoading.value,
        navigator = navigator,
    )

    ComposableObserver(state = loginResult,
        onLoading = {
            isLoading.value = true
        },
        onSuccess = {
            isLoading.value = false
            val token = it.loginResult?.token
            val user = it.loginResult
            val message = it.message.toString()

            if (token != null && user != null) {
                saveSession(loginViewModel, activity, token, user)
            }

            stringResource(id = R.string.login_result, message).showToast(activity)
        },
        onError = { message ->
            isLoading.value = false
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
    modifier: Modifier = Modifier,
    onClick: (String, String) -> Unit,
    isLoading: Boolean = false,
    navigator: DestinationsNavigator? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var emailErrorMessage by remember { mutableStateOf("") }
    var passwordErrorMessage by remember { mutableStateOf("") }

    val visibility = if (isLoading) 1f else 0f

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimension.SIZE_24)
            .verticalScroll(rememberScrollState())
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(visibility),
            color = seed,
            strokeWidth = Dimension.SIZE_4,
            progress = 0.5f,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Top,
        ) {

            val context = getContext()

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp),
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
                modifier = Modifier.alpha(0.8f),
                textAlign = TextAlign.Start,
                text = stringResource(R.string.sign_in),
                style = typography.h2
            )
            ValidationForm(modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_18),
                state = emailValidation,
                onFormValueChange = {
                    emailValidation.value = emailValidation.value.copy(
                        text = it
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                ),
                onImeKeyAction = {
                    keyboardController?.hide()
                })
            ValidationForm(modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_18),
                state = passwordValidation,
                isPasswordForm = true,
                onFormValueChange = {
                    passwordValidation.value = passwordValidation.value.copy(
                        text = it
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                onImeKeyAction = {
                    keyboardController?.hide()
                })
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = Dimension.SIZE_8)
                        .clickable(onClick = {
                            context
                                .getString(R.string.on_click_handler)
                                .showToast(context)
                        }),
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.End,
                    text = stringResource(R.string.forgot_password),
                    style = typography.body2
                )
            }
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = seed, contentColor = Color.White
                ),
                shape = MaterialTheme.shapes.small,
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
            ButtonWithDrawableStart(modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_28),
                buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
                textButton = stringResource(R.string.sign_in_with_google),
                textColor = Color.Black,
                borderStroke = BorderStroke(Dimension.SIZE_1, Color.Black),
                drawableStart = painterResource(R.drawable.ic_google),
                onClick = {
                    context.getString(R.string.on_click_handler).showToast(context)
                })
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