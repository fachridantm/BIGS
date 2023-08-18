package id.belitong.bigs.compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import id.belitong.bigs.compose.R

private val poppinsBold = FontFamily(Font(R.font.poppins_bold))
private val poppinsSemiBold = FontFamily(Font(R.font.poppins_semi_bold))
private val poppinsMedium = FontFamily(Font(R.font.poppins_medium))
private val poppinsRegular = FontFamily(Font(R.font.poppins_regular))

// Set of Material typography styles to start with
val typography = Typography(
    defaultFontFamily = poppinsMedium,
    h1 = TextStyle(
        fontFamily = poppinsSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = TextSize.SIZE_24,
    ),
    h2 = TextStyle(
        fontFamily = poppinsBold,
        fontWeight = FontWeight.Bold,
        fontSize = TextSize.SIZE_22,
    ),
    h3 = TextStyle(
        fontFamily = poppinsSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = TextSize.SIZE_20,
    ),
    h4 = TextStyle(
        fontFamily = poppinsMedium,
        fontWeight = FontWeight.Medium,
        fontSize = TextSize.SIZE_18,
    ),
    h5 = TextStyle(
        fontFamily = poppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = TextSize.SIZE_18,
    ),
    body1 = TextStyle(
        fontFamily = poppinsSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = TextSize.SIZE_16,
    ),
    body2 = TextStyle(
        fontFamily = poppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = TextSize.SIZE_16,
    ),
    subtitle1 = TextStyle(
        fontFamily = poppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = TextSize.SIZE_14,
    ),
    subtitle2 = TextStyle(
        fontFamily = poppinsMedium,
        fontWeight = FontWeight.Medium,
        fontSize = TextSize.SIZE_14,
    ),
    button = TextStyle(
        fontFamily = poppinsSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = TextSize.SIZE_16,
    ),
    caption = TextStyle(
        fontFamily = poppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = TextSize.SIZE_12,
    ),
    overline = TextStyle(
        fontFamily = poppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = TextSize.SIZE_12,
    ),
)