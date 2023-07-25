package id.belitong.bigs.ui.composable.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.belitong.bigs.R
import id.belitong.bigs.ui.theme.md_theme_dark_secondary
import id.belitong.bigs.ui.theme.typography

@Composable
fun ButtonWithDrawableEnd(
    modifier: Modifier = Modifier,
    buttonColor: ButtonColors,
    textButton: String,
    textColor: Color,
    borderStroke: BorderStroke = BorderStroke(1.dp, Color.Unspecified),
    drawableEnd: Painter,
    drawableTint: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = buttonColor,
        shape = MaterialTheme.shapes.small,
        border = borderStroke,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = textButton,
                maxLines = 1,
                style = typography.button,
                textAlign = TextAlign.Center,
                color = textColor,
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Icon(
                painter = drawableEnd,
                contentDescription = null,
                tint = drawableTint,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun ButtonWithDrawableStart(
    modifier: Modifier = Modifier,
    buttonColor: ButtonColors,
    textButton: String,
    textColor: Color,
    borderStroke: BorderStroke = BorderStroke(1.dp, Color.Unspecified),
    drawableStart: Painter,
    drawableTint: Color = Color.Unspecified,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = buttonColor,
        shape = MaterialTheme.shapes.small,
        border = borderStroke,
        enabled = enabled,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = drawableStart,
                contentDescription = null,
                tint = drawableTint,
                modifier = Modifier
                    .padding(end = 16.dp)
            )
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = textButton,
                maxLines = 1,
                style = typography.button,
                textAlign = TextAlign.Center,
                color = textColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonWithDrawableEndPreview() {
    ButtonWithDrawableEnd(
        modifier = Modifier.padding(16.dp),
        buttonColor = ButtonDefaults.buttonColors(containerColor = md_theme_dark_secondary),
        textButton = "Explore Now",
        textColor = Color.Black,
        drawableEnd = painterResource(R.drawable.ic_explore_now),
        drawableTint = Color.Black,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ButtonWithDrawableStartPreview() {
    ButtonWithDrawableStart(
        modifier = Modifier.padding(16.dp),
        buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
        textButton = stringResource(R.string.sign_in_with_google),
        textColor = Color.Black,
        borderStroke = BorderStroke(1.dp, Color.Black),
        drawableStart = painterResource(R.drawable.ic_google),
        onClick = {}
    )
}