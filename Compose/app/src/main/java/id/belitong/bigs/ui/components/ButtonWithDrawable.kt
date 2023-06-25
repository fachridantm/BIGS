package id.belitong.bigs.ui.components

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
    drawableEnd: Painter,
    drawableTint: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = buttonColor,
        shape = MaterialTheme.shapes.small,
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 32.dp),
                text = textButton,
                style = typography.button,
                textAlign = TextAlign.Center,
                color = textColor,
            )
            Icon(
                painter = drawableEnd,
                contentDescription = null,
                tint = drawableTint,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonWithDrawableEndPreview() {
    ButtonWithDrawableEnd(
        buttonColor = ButtonDefaults.buttonColors(containerColor = md_theme_dark_secondary),
        textButton = "Explore Now",
        textColor = Color.Black,
        drawableEnd = painterResource(R.drawable.ic_explore_now),
        drawableTint = Color.Black,
        onClick = {}
    )
}
