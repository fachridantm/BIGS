package id.belitong.bigs.ui.screen.auth.login

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.belitong.bigs.R
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.ui.components.ButtonWithDrawableStart
import id.belitong.bigs.ui.components.FormTextField
import id.belitong.bigs.ui.theme.md_theme_dark_secondary
import id.belitong.bigs.ui.theme.seed
import id.belitong.bigs.ui.theme.typography

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onClickLogin: () -> Unit,
) {
    LoginScreenContent(
        modifier = modifier,
        onClickLogin = { email, password ->
            viewModel.loginUser(email, password)
            onClickLogin()
        }
    )
    viewModel.result.collectAsState(initial = Resource.Loading).value.let {
        when (it) {
            is Resource.Loading -> {
                Log.d("LoginScreen", "Loading")
            }

            is Resource.Success -> {
                val token = it.data.loginResult?.token
                val user = it.data.loginResult
                val message = it.data.message.toString()

                if (!token.isNullOrEmpty() && user != null) {
                    viewModel.saveSession(token, user)
                } else {
                    Log.e("LoginScreen", "Error: $message")
                }
            }

            is Resource.Error -> {
                Log.e("LoginScreen", "Error: ${it.errorMessage}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    onClickLogin: (String, String) -> Unit,
) {
    var showError by rememberSaveable { mutableStateOf(false) }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    var textEmailError by rememberSaveable { mutableStateOf("") }
    var textPasswordError by rememberSaveable { mutableStateOf("") }
    var textEmail by rememberSaveable { mutableStateOf("") }
    var textPassword by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Top,
        ) {
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
                modifier = Modifier,
                textAlign = TextAlign.Start,
                text = stringResource(R.string.sign_in),
                style = typography.h2
            )
            FormTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                textValue = textEmail,
                textLabel = stringResource(R.string.email_address),
                textError = textEmailError,
                isError = showError,
                trailingIcon = {
                    if (showError) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = null
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = {
                    textEmail = it
                }
            )
            FormTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                textValue = textPassword,
                textLabel = stringResource(R.string.password),
                textError = textPasswordError,
                isError = showError,
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisibility = !passwordVisibility }
                    ) {
                        if (showError) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = null
                            )
                        }
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    textPassword = it
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.End,
                    text = stringResource(R.string.forgot_password),
                    style = typography.body2
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = seed,
                    contentColor = Color.White
                ),
                shape = MaterialTheme.shapes.small,
                onClick = {
                    when {
                        textEmail.isEmpty() -> {
                            showError = true
                            textEmailError = "Email cannot be empty!"
                            if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                                textEmailError = "Please input a valid email address!"
                            } else {
                                showError = false
                                textEmailError = ""
                            }
                        }

                        textPassword.isEmpty() -> {
                            showError = true
                            textPasswordError = "Password cannot be empty!"
                            if (textPassword.length < 8) {
                                textPasswordError = "Password should be at least 8 characters long!"
                            } else {
                                showError = false
                                textPasswordError = ""
                            }
                        }

                        else -> {
                            showError = false
                            textEmailError = ""
                            textPasswordError = ""
                            onClickLogin(textEmail, textPassword)
                        }
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.sign_in), style = typography.button
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.or),
                    style = typography.h4
                )
            }
            ButtonWithDrawableStart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
                textButton = stringResource(R.string.sign_in_with_google),
                textColor = Color.Black,
                borderStroke = BorderStroke(1.dp, Color.Black),
                drawableStart = painterResource(R.drawable.ic_google),
                onClick = {}
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.dont_have_an_account),
                    color = Color.Black,
                    style = typography.body1
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
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
        onClickLogin = { email, password ->
            Log.d("LoginScreen", "email: $email, password: $password")
        }
    )
}