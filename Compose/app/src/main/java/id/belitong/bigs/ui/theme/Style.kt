package id.belitong.bigs.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import id.belitong.bigs.R

val textBold = FontFamily(Font(R.font.poppins_bold))

val textSemibold = FontFamily(Font(R.font.poppins_semi_bold))

val textMedium = FontFamily(Font(R.font.poppins_medium))

val textRegular = FontFamily(Font(R.font.poppins_regular))

val textSemiboldH1 = TextStyle(
    fontFamily = textSemibold,
    fontSize = 24.sp,
)

val textBoldH2 = TextStyle(
    fontFamily = textBold,
    fontSize = 22.sp,
)

val textSemiboldH3 = TextStyle(
    fontFamily = textSemibold,
    fontSize = 20.sp,
)

val textMediumSubheader = TextStyle(
    fontFamily = textMedium,
    fontSize = 18.sp,
)

val textRegularSubheader = TextStyle(
    fontFamily = textRegular,
    fontSize = 18.sp,
)

val textSemiboldTitle = TextStyle(
    fontFamily = textSemibold,
    fontSize = 16.sp,
)

val textRegularTitle = TextStyle(
    fontFamily = textRegular,
    fontSize = 16.sp,
)

val textRegularSubtitle = TextStyle(
    fontFamily = textRegular,
    fontSize = 14.sp,
)

val textSemiboldH1Center = TextStyle(
    fontFamily = textSemibold,
    fontSize = 24.sp,
    textAlign = TextAlign.Center,
)

val textSemiboldTitleCenter = TextStyle(
    fontFamily = textSemibold,
    fontSize = 16.sp,
    textAlign = TextAlign.Center,
)