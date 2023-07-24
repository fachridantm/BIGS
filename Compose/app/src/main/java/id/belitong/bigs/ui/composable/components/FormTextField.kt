package id.belitong.bigs.ui.composable.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.belitong.bigs.R
import id.belitong.bigs.ui.theme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    textValue: String = "",
    textLabel: String,
    textError: String = "",
    isError: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        textStyle = typography.body1,
        isError = isError,
        singleLine = true,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        value = textValue,
        onValueChange = onValueChange,
        label = {
            Text(
                text = textLabel,
                style = typography.body1
            )
        }
    )
    if (isError) {
        Text(
            text = textError,
            color = Color.Red,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp),
            style = typography.caption
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FormTextFieldPreview() {
    FormTextField(
        modifier = Modifier.padding(16.dp),
        textLabel = stringResource(R.string.email_address)
    )
}