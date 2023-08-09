package id.belitong.bigs.compose.ui.composable.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun BasicLottieAnimation(
    modifier: Modifier = Modifier,
    resId: Int
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}

@Composable
fun LottieAnimationForScanPlant(
    modifier: Modifier = Modifier,
    resIdSucces: Int,
    resIdFailed: Int,
    isFailed: Boolean = false,
    isSuccess: Boolean = false
) {
    val clipSpecs = LottieClipSpec.Progress(
        min = if (isFailed) 0.499f else 0.0f,
        max = if (isSuccess) 0.44f else if (isFailed) 0.95f else 0.282f
    )

    val compositionSuccess by rememberLottieComposition(LottieCompositionSpec.RawRes(resIdSucces))
    val compositionFailed by rememberLottieComposition(LottieCompositionSpec.RawRes(resIdFailed))

    LottieAnimation(
        modifier = modifier,
        composition = if (isSuccess) compositionSuccess else compositionFailed,
        iterations = if (isSuccess || isFailed) 1 else LottieConstants.IterateForever,
        clipSpec = clipSpecs
    )
}