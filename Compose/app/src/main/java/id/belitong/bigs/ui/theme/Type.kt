package id.belitong.bigs.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import id.belitong.bigs.R

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
        fontSize = 24.sp,
    ),
    h2 = TextStyle(
        fontFamily = PoppinsBold,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    ),
    h3 = TextStyle(
        fontFamily = PoppinsBold,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    h4 = TextStyle(
        fontFamily = PoppinsMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
    ),
    h5 = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    body1 = TextStyle(
        fontFamily = PoppinsSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = PoppinsMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    button = TextStyle(
        fontFamily = PoppinsSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    caption = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    overline = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
)