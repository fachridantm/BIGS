package id.belitong.bigs.compose.ui.screen.auth.register

import android.util.Log
import android.util.Patterns
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.utils.emptyString
import id.belitong.bigs.compose.core.utils.showToast
import id.belitong.bigs.compose.ui.composable.components.BasicLottieAnimation
import id.belitong.bigs.compose.ui.composable.components.ValidationForm
import id.belitong.bigs.compose.ui.composable.model.FormValidation
import id.belitong.bigs.compose.ui.composable.utils.ComposableObserver
import id.belitong.bigs.compose.ui.composable.utils.getContext
import id.belitong.bigs.compose.ui.navigation.AuthNavGraph
import id.belitong.bigs.compose.ui.screen.destinations.LoginScreenDestination
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.md_theme_dark_secondary
import id.belitong.bigs.compose.ui.theme.seed
import id.belitong.bigs.compose.ui.theme.typography

@AuthNavGraph
@Destination
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navigator: DestinationsNavigator? = null,
) {
    val context = getContext()

    val registerResult = registerViewModel.result.observeAsState()
    val isLoading = remember { mutableStateOf(false) }

    BackHandler {
        navigator?.navigateUp()
    }

    RegisterScreenContent(
        onClick = { name, email, password ->
            registerViewModel.registerUser(name, email, password)
        },
        isLoading = isLoading.value,
        navigator = navigator
    )

    ComposableObserver(state = registerResult,
        onLoading = {
            isLoading.value = true
        },
        onSuccess = {
            isLoading.value = false
            it.message?.showToast(context)
            navigator?.navigate(LoginScreenDestination)
        },
        onError = { message ->
            isLoading.value = false
            message.showToast(context)
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    onClick: (String, String, String) -> Unit,
    isLoading: Boolean = false,
    navigator: DestinationsNavigator? = null
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var emailErrorMessage by remember { mutableStateOf("") }
    var nameErrorMessage by remember { mutableStateOf("") }
    var phoneNumberErrorMessage by remember { mutableStateOf("") }
    var passwordErrorMessage by remember { mutableStateOf("") }

    val visibility = if (isLoading) 1f else 0f

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimension.SIZE_24)
            .verticalScroll(rememberScrollState())
    ) {
        BasicLottieAnimation(
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(visibility),
            resId = R.raw.loading,
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

            val nameValidation =
                remember {
                    mutableStateOf(
                        FormValidation(
                            hint = context.getString(R.string.full_name),
                            errorMessage = nameErrorMessage
                        )
                    )
                }

            val phoneNumberValidation =
                remember {
                    mutableStateOf(
                        FormValidation(
                            hint = context.getString(R.string.phone_number),
                            errorMessage = phoneNumberErrorMessage
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
            val name = nameValidation.value.text
            val phoneNumber = phoneNumberValidation.value.text
            val password = passwordValidation.value.text

            emailErrorMessage = if (email.isEmpty()) {
                stringResource(R.string.email_not_allowed_to_be_empty)
            } else {
                stringResource(R.string.invalid_email_format)
            }

            nameErrorMessage = if (name.isEmpty()) {
                stringResource(R.string.name_not_allowed_to_be_empty)
            } else {
                emptyString()
            }

            phoneNumberErrorMessage = if (phoneNumber.isEmpty()) {
                stringResource(R.string.phone_not_allowed_to_be_empty)
            } else {
                stringResource(R.string.invalid_phone_number_format)
            }

            passwordErrorMessage = if (password.isEmpty()) {
                stringResource(R.string.password_not_allowed_to_be_empty)
            } else {
                stringResource(R.string.minimum_password_length)
            }

            val buttonValidation =
                email.isNotEmpty() &&
                        Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                        name.isNotEmpty() &&
                        phoneNumber.isNotEmpty() &&
                        Patterns.PHONE.matcher(phoneNumber).matches() &&
                        password.isNotEmpty() &&
                        password.length >= 8

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_64),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier
                        .width(166.dp)
                        .height(253.15.dp),
                    contentDescription = null,
                    painter = painterResource(R.drawable.img_holding_earth)
                )
            }
            Text(
                textAlign = TextAlign.Start,
                text = stringResource(R.string.sign_up),
                style = typography.h2
            )
            ValidationForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_24),
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
                }
            )
            ValidationForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_18),
                state = nameValidation,
                onFormValueChange = {
                    nameValidation.value = nameValidation.value.copy(
                        text = it
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                onImeKeyAction = {
                    keyboardController?.hide()
                }
            )
            ValidationForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_18),
                state = phoneNumberValidation,
                onFormValueChange = {
                    phoneNumberValidation.value = phoneNumberValidation.value.copy(
                        text = it
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Phone
                ),
                onImeKeyAction = {
                    keyboardController?.hide()
                }
            )
            ValidationForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_18),
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
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = Dimension.SIZE_30,
                        start = Dimension.SIZE_32,
                        end = Dimension.SIZE_32
                    ),
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

                    nameValidation.value = nameValidation.value.copy(
                        isError = name.isEmpty(),
                        errorMessage = nameErrorMessage
                    )

                    phoneNumberValidation.value = phoneNumberValidation.value.copy(
                        isError = phoneNumber.isEmpty() || !Patterns.PHONE.matcher(phoneNumber)
                            .matches(),
                        errorMessage = phoneNumberErrorMessage
                    )

                    passwordValidation.value = passwordValidation.value.copy(
                        isError = password.isEmpty() || password.length < 8,
                        errorMessage = passwordErrorMessage
                    )

                    if (buttonValidation) {
                        onClick(name, email, password)
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.sign_up), style = typography.button
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimension.SIZE_100),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.already_have_an_account),
                    color = Color.Black,
                    style = typography.body1
                )
                Text(
                    modifier = Modifier
                        .padding(start = Dimension.SIZE_4)
                        .clickable(
                            enabled = !isLoading,
                            onClick = {
                                navigator?.navigate(LoginScreenDestination)
                            }
                        ),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.sign_in),
                    color = md_theme_dark_secondary,
                    style = typography.body1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreenContent(
        onClick = { name, email, password ->
            Log.d("RegisterScreen", "name: $name, email: $email, password: $password")
        }
    )
}