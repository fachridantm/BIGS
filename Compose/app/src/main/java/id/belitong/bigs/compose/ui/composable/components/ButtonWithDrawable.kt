package id.belitong.bigs.compose.ui.composable.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.md_theme_dark_secondary
import id.belitong.bigs.compose.ui.theme.md_theme_dark_tertiary
import id.belitong.bigs.compose.ui.theme.typography

@Composable
fun ButtonWithDrawableEnd(
    modifier: Modifier = Modifier.shadow(
        elevation = Dimension.SIZE_4,
        shape = RoundedCornerShape(Dimension.SIZE_10)
    ),
    buttonColor: ButtonColors,
    textButton: String,
    textColor: Color,
    textStyle: TextStyle = typography.button,
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
            modifier = Modifier.padding(
                horizontal = Dimension.SIZE_32,
                vertical = Dimension.SIZE_8
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = textButton,
                maxLines = 1,
                style = textStyle,
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
    modifier: Modifier = Modifier.shadow(
        elevation = Dimension.SIZE_4,
        shape = RoundedCornerShape(Dimension.SIZE_10)
    ),
    buttonColor: ButtonColors,
    textButton: String,
    textColor: Color,
    textStyle: TextStyle = typography.button,
    borderStroke: BorderStroke = BorderStroke(Dimension.SIZE_1, Color.Unspecified),
    drawableStart: Painter,
    drawableTint: Color = Color.Unspecified,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    innerPadding: PaddingValues = PaddingValues(
        horizontal = Dimension.SIZE_32,
        vertical = Dimension.SIZE_12
    ),
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = Dimension.SIZE_2,
        pressedElevation = Dimension.SIZE_0,
        disabledElevation = Dimension.SIZE_0
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    textPadding: PaddingValues = PaddingValues(horizontal = Dimension.SIZE_12),
    iconPadding: PaddingValues = PaddingValues(end = Dimension.SIZE_12),
    textOverflow: TextOverflow = TextOverflow.Visible,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = buttonColor,
        shape = shape,
        border = borderStroke,
        enabled = enabled,
        modifier = modifier,
        elevation = elevation,
        contentPadding = innerPadding,
        interactionSource = interactionSource
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement,
        ) {
            Icon(
                modifier = Modifier.padding(iconPadding),
                painter = drawableStart,
                contentDescription = null,
                tint = drawableTint
            )
            Text(
                modifier = Modifier.padding(textPadding),
                text = textButton,
                maxLines = 1,
                style = textStyle,
                textAlign = TextAlign.Center,
                color = textColor,
                overflow = textOverflow
            )
        }
    }
}

@Composable
fun ButtonWithDrawableStart(
    modifier: Modifier = Modifier
        .shadow(
            elevation = Dimension.SIZE_4,
            shape = RoundedCornerShape(Dimension.SIZE_10)
        ),
    buttonColor: ButtonColors,
    textButton: String,
    textColor: Color,
    textStyle: TextStyle = typography.h3,
    borderStroke: BorderStroke = BorderStroke(Dimension.SIZE_1, Color.Unspecified),
    drawableImageStart: Painter,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(Dimension.SIZE_9),
    innerPadding: PaddingValues = PaddingValues(
        horizontal = Dimension.SIZE_0,
        vertical = Dimension.SIZE_0
    ),
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = Dimension.SIZE_2,
        pressedElevation = Dimension.SIZE_0,
        disabledElevation = Dimension.SIZE_0
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    textPadding: PaddingValues = PaddingValues(
        horizontal = Dimension.SIZE_0,
        vertical = Dimension.SIZE_0
    ),
    imagePadding: PaddingValues = PaddingValues(end = Dimension.SIZE_4),
    textOverflow: TextOverflow = TextOverflow.Visible,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = buttonColor,
        shape = shape,
        border = borderStroke,
        enabled = enabled,
        modifier = modifier,
        elevation = elevation,
        contentPadding = innerPadding,
        interactionSource = interactionSource
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement,
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(imagePadding),
                painter = drawableImageStart,
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(textPadding),
                text = textButton,
                maxLines = 1,
                style = textStyle,
                textAlign = TextAlign.Center,
                color = textColor,
                overflow = textOverflow
            )
        }
    }
}


@Composable
fun ButtonWithDrawableTop(
    modifier: Modifier = Modifier.shadow(
        elevation = Dimension.SIZE_4,
        shape = RoundedCornerShape(Dimension.SIZE_10)
    ),
    buttonColor: ButtonColors,
    textButton: String,
    textColor: Color,
    textStyle: TextStyle = typography.body2,
    borderStroke: BorderStroke = BorderStroke(Dimension.SIZE_1, Color.Unspecified),
    drawableStart: Painter,
    drawableTint: Color = Color.Unspecified,
    buttonElevation: ButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = Dimension.SIZE_2,
        pressedElevation = Dimension.SIZE_0,
        disabledElevation = Dimension.SIZE_0
    ),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = buttonColor,
        shape = RoundedCornerShape(Dimension.SIZE_10),
        border = borderStroke,
        enabled = enabled,
        contentPadding = PaddingValues(Dimension.SIZE_12),
        interactionSource = interactionSource,
        elevation = buttonElevation,
        modifier = modifier
            .height(125.dp)
            .width(110.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = Dimension.SIZE_16),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Icon(
                painter = drawableStart,
                contentDescription = null,
                tint = drawableTint,
            )
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = Dimension.SIZE_16),
                text = textButton,
                maxLines = 2,
                overflow = TextOverflow.Visible,
                style = textStyle,
                textAlign = TextAlign.Center,
                color = textColor,
            )
        }
    }
}

@Composable
fun ButtonWithDrawableStartAndEnd(
    modifier: Modifier = Modifier.shadow(
        elevation = Dimension.SIZE_4,
        shape = RoundedCornerShape(Dimension.SIZE_10)
    ),
    buttonColor: ButtonColors,
    textButton: String,
    textColor: Color,
    textStyle: TextStyle = typography.button,
    borderStroke: BorderStroke = BorderStroke(Dimension.SIZE_1, Color.Unspecified),
    drawableStart: Painter,
    drawableEnd: Painter,
    drawableTint: Color = Color.Unspecified,
    buttonElevation: ButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = Dimension.SIZE_2,
        pressedElevation = Dimension.SIZE_0,
        disabledElevation = Dimension.SIZE_0
    ),
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(Dimension.SIZE_10),
    innerPadding: PaddingValues = PaddingValues(
        horizontal = Dimension.SIZE_12,
        vertical = Dimension.SIZE_12
    ),
    textPadding: PaddingValues = PaddingValues(start = Dimension.SIZE_16),
    iconPadding: PaddingValues = PaddingValues(Dimension.SIZE_0),
    textOverflow: TextOverflow = TextOverflow.Visible,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = buttonColor,
        shape = shape,
        border = borderStroke,
        elevation = buttonElevation,
        enabled = enabled,
        modifier = modifier,
        contentPadding = innerPadding,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement,
        ) {
            Icon(
                modifier = Modifier.padding(iconPadding),
                painter = drawableStart,
                contentDescription = null,
                tint = drawableTint
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(textPadding),
                text = textButton,
                maxLines = 1,
                style = textStyle,
                textAlign = TextAlign.Start,
                color = textColor,
                overflow = textOverflow
            )
            Icon(
                modifier = Modifier.padding(iconPadding),
                painter = drawableEnd,
                contentDescription = null,
                tint = drawableTint
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonWithDrawableEndPreview() {
    ButtonWithDrawableEnd(
        modifier = Modifier.padding(Dimension.SIZE_16),
        buttonColor = ButtonDefaults.buttonColors(containerColor = md_theme_dark_secondary),
        textButton = stringResource(R.string.explore_now),
        textColor = Color.Black,
        drawableEnd = painterResource(R.drawable.ic_explore_now),
        drawableTint = Color.Black,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ButtonWithDrawableStartPreview() {
    Column {
        ButtonWithDrawableStart(
            modifier = Modifier.padding(Dimension.SIZE_16),
            buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
            textButton = stringResource(R.string.sign_in_with_google),
            textColor = Color.Black,
            borderStroke = BorderStroke(Dimension.SIZE_1, Color.Black),
            drawableStart = painterResource(R.drawable.ic_google),
            onClick = {}
        )
        ButtonWithDrawableStart(
            modifier = Modifier.padding(Dimension.SIZE_16),
            buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
            textButton = stringResource(R.string.tour_guide),
            textColor = Color.Black,
            borderStroke = BorderStroke(Dimension.SIZE_1, Color.Black),
            drawableImageStart = painterResource(R.drawable.img_tour_guide),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonWithDrawableTopPreview() {
    ButtonWithDrawableTop(
        modifier = Modifier.padding(Dimension.SIZE_16),
        buttonColor = ButtonDefaults.buttonColors(containerColor = md_theme_dark_tertiary),
        textButton = stringResource(R.string.geosites),
        textColor = Color.Black,
        drawableStart = painterResource(R.drawable.ic_geosites),
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ButtonWithDrawableStartAndEndPreview() {
    ButtonWithDrawableStartAndEnd(
        modifier = Modifier.padding(Dimension.SIZE_16),
        buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
        textButton = stringResource(R.string.edit_profile),
        textColor = Color.Black,
        borderStroke = BorderStroke(1.dp, Color.Black),
        drawableStart = painterResource(R.drawable.ic_profile),
        drawableEnd = painterResource(R.drawable.ic_arrow_right),
        onClick = {}
    )
}