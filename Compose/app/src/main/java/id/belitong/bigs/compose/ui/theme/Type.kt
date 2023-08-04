package id.belitong.bigs.compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import id.belitong.bigs.compose.R

private val PoppinsBold = FontFamily(Font(R.font.poppins_bold))
private val PoppinsSemiBold = FontFamily(Font(R.font.poppins_semi_bold))
private val PoppinsMedium = FontFamily(Font(R.font.poppins_medium))
private val PoppinsRegular = FontFamily(Font(R.font.poppins_regular))

// Set of Material typography styles to start with
val typography = Typography(
    defaultFontFamily = PoppinsMedium,
    h1 = TextStyle(
        fontFamily = PoppinsSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = TextS.SIZE_24,
    ),
    h2 = TextStyle(
        fontFamily = PoppinsBold,
        fontWeight = FontWeight.Bold,
        fontSize = TextS.SIZE_22,
    ),
    h3 = TextStyle(
        fontFamily = PoppinsSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = TextS.SIZE_20,
    ),
    h4 = TextStyle(
        fontFamily = PoppinsMedium,
        fontWeight = FontWeight.Medium,
        fontSize = TextS.SIZE_18,
    ),
    h5 = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = TextS.SIZE_18,
    ),
    body1 = TextStyle(
        fontFamily = PoppinsSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = TextS.SIZE_16,
    ),
    body2 = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = TextS.SIZE_16,
    ),
    subtitle1 = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = TextS.SIZE_14,
    ),
    subtitle2 = TextStyle(
        fontFamily = PoppinsMedium,
        fontWeight = FontWeight.Medium,
        fontSize = TextS.SIZE_14,
    ),
    button = TextStyle(
        fontFamily = PoppinsSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = TextS.SIZE_16,
    ),
    caption = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = TextS.SIZE_12,
    ),
    overline = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = TextS.SIZE_12,
    ),
)