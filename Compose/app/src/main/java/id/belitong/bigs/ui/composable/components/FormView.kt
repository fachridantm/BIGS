package id.belitong.bigs.ui.composable.components

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import id.belitong.bigs.R
import id.belitong.bigs.core.domain.model.FormValidation
import id.belitong.bigs.core.utils.emptyString
import id.belitong.bigs.ui.theme.Dimension
import id.belitong.bigs.ui.theme.md_theme_light_error
import id.belitong.bigs.ui.theme.seed
import id.belitong.bigs.ui.theme.typography

typealias OnFormValueChange = (String) -> Unit
typealias OnImeKeyAction = (KeyboardOptions) -> Unit

@Composable
fun ValidationForm(
    modifier: Modifier = Modifier,
    isPasswordForm: Boolean = false,
    onFormValueChange: OnFormValueChange? = null,
    onImeKeyAction: OnImeKeyAction? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    state: MutableState<FormValidation>,
    errorTag: String = emptyString()
) {
    val data by state
    val showPassword = remember { mutableStateOf(false) }

    FormTextField(
        value = data.text,
        onFormValueChange = onFormValueChange,
        isError = data.isError,
        errorMsg = data.errorMessage,
        label = data.hint,
        modifier = modifier,
        trailingIcon = if (isPasswordForm) passwordToggleView(showPassword.value) {
            showPassword.value = !showPassword.value
        } else trailingIcon,
        visualTransformation = if (!showPassword.value && isPasswordForm) PasswordVisualTransformation() else VisualTransformation.None,
        onImeKeyAction = onImeKeyAction,
        leadingIcon = leadingIcon,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        maxLines = maxLines,
        errorTag = errorTag
    )
}

@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    value: String,
    onFormValueChange: OnFormValueChange?,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onImeKeyAction: OnImeKeyAction?,
    readOnly: Boolean = false,
    label: String = emptyString(),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    errorMsg: String = emptyString(),
    errorTag: String = emptyString()
) {
    Column(
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(),
            value = value,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                onFormValueChange?.invoke(it)
            },
            enabled = !readOnly,
            singleLine = singleLine,
            readOnly = readOnly,
            visualTransformation = visualTransformation,
            textStyle = typography.body1,
            label = {
                Text(
                    text = label,
                    style = typography.body1,
                )
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(onAny = {
                onImeKeyAction?.invoke(keyboardOptions)
            }),
            maxLines = maxLines,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                trailingIconColor = Color.Gray,
                backgroundColor = Color.Transparent,
                focusedLabelColor = seed,
                unfocusedLabelColor = Color.Gray,
                disabledLabelColor = Color.Gray,
                focusedBorderColor = seed,
                unfocusedBorderColor = Color.Gray,
                disabledBorderColor = Color.Gray,
            )
        )

        if (isError) {
            Text(
                modifier = Modifier
                    .padding(start = Dimension.SIZE_12, top = Dimension.SIZE_8)
                    .semantics { testTag = errorTag },
                text = errorMsg,
                style = typography.caption.copy(color = md_theme_light_error),
            )
        }
    }
}

@Composable
fun passwordToggleView(showPassword: Boolean, onClick: () -> Unit): @Composable () -> Unit {
    val icon = if (showPassword) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility

    return {
        IconButton(onClick = onClick) {
            Image(
                icon,
                contentDescription = stringResource(R.string.password_toggle_content_desc),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ValidationFormPreview() {

    val emailValidation =
        remember {
            mutableStateOf(
                FormValidation(
                    hint = "Email Address",
                    errorMessage = "Email cannot be empty!"
                )
            )
        }

    ValidationForm(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.SIZE_18),
        state = emailValidation,
        onFormValueChange = {
            emailValidation.value = emailValidation.value.copy(
                text = it,
                isError = it.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(it).matches()
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    )
}