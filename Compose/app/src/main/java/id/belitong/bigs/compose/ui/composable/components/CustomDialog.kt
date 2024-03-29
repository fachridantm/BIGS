package id.belitong.bigs.compose.ui.composable.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.Plant
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.md_theme_light_error
import id.belitong.bigs.compose.ui.theme.md_theme_light_primary
import id.belitong.bigs.compose.ui.theme.typography

@Composable
fun ScanResultDialog(
    modifier: Modifier = Modifier,
    plant: Plant? = null,
    isFailed: Boolean = false,
    isSuccess: Boolean = false,
    onClickDetails: () -> Unit = {},
    onClickCancel: () -> Unit = {},
    onClickAddData: () -> Unit = {}
) {
    if (isSuccess) {
        Dialog(
            onDismissRequest = onClickCancel,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = Dimension.SIZE_12),
                shape = RoundedCornerShape(Dimension.SIZE_12),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(Dimension.SIZE_18),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LottieAnimationForScanPlant(
                        modifier = Modifier.size(150.dp),
                        resIdSucces = R.raw.success,
                        resIdFailed = R.raw.error,
                        isFailed = isFailed,
                        isSuccess = isSuccess
                    )
                    Text(
                        text = plant?.name ?: stringResource(R.string.dash),
                        style = typography.h3,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                    Text(
                        text = plant?.latin ?: stringResource(R.string.dash),
                        style = typography.body2,
                        color = md_theme_light_primary,
                        fontStyle = FontStyle.Italic,
                        maxLines = 2
                    )
                    Button(
                        modifier = Modifier.padding(top = Dimension.SIZE_12),
                        onClick = onClickDetails,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = md_theme_light_primary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(Dimension.SIZE_5),
                        contentPadding = PaddingValues(
                            horizontal = Dimension.SIZE_18,
                            vertical = Dimension.SIZE_8
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.details),
                            style = typography.body2,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    } else {
        Dialog(
            onDismissRequest = onClickCancel,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = Dimension.SIZE_12),
                shape = RoundedCornerShape(Dimension.SIZE_12),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(Dimension.SIZE_18),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LottieAnimationForScanPlant(
                        modifier = Modifier.size(150.dp),
                        resIdSucces = R.raw.success,
                        resIdFailed = R.raw.error,
                        isFailed = isFailed,
                        isSuccess = isSuccess
                    )
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.data_not_found),
                        style = typography.body2,
                        color = md_theme_light_error,
                        maxLines = 2
                    )
                    Row(
                        modifier = Modifier.padding(top = Dimension.SIZE_12),
                        horizontalArrangement = Arrangement.spacedBy(Dimension.SIZE_8),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = Dimension.SIZE_8),
                            onClick = onClickCancel,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = md_theme_light_error,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(Dimension.SIZE_12),
                            contentPadding = PaddingValues(
                                start = Dimension.SIZE_8,
                                end = Dimension.SIZE_8,
                                top = Dimension.SIZE_2,
                                bottom = Dimension.SIZE_2
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = typography.subtitle1,
                                color = Color.White,
                            )
                        }
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = Dimension.SIZE_8),
                            onClick = onClickAddData,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = md_theme_light_primary,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(Dimension.SIZE_12),
                            contentPadding = PaddingValues(
                                start = Dimension.SIZE_8,
                                end = Dimension.SIZE_8,
                                top = Dimension.SIZE_2,
                                bottom = Dimension.SIZE_2
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.add_data),
                                style = typography.subtitle1,
                                color = Color.White,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LogoutDialog(
    modifier: Modifier = Modifier,
    onClickCancel: () -> Unit = {},
    onClickLogout: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onClickCancel,
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = Dimension.SIZE_12),
            shape = RoundedCornerShape(Dimension.SIZE_12),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(Dimension.SIZE_24),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.logout_message),
                    style = typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    maxLines = 1
                )
                Row(
                    modifier = Modifier.padding(top = Dimension.SIZE_26),
                    horizontalArrangement = Arrangement.spacedBy(Dimension.SIZE_8),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = Dimension.SIZE_8),
                        onClick = onClickCancel,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(Dimension.SIZE_12),
                        contentPadding = PaddingValues(
                            start = Dimension.SIZE_16,
                            end = Dimension.SIZE_16,
                            top = Dimension.SIZE_2,
                            bottom = Dimension.SIZE_2
                        ),
                        border = BorderStroke(
                            width = Dimension.SIZE_1,
                            color = Color.Black
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.no),
                            style = typography.subtitle1,
                            color = Color.Black,
                        )
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = Dimension.SIZE_8),
                        onClick = onClickLogout,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = md_theme_light_error,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(Dimension.SIZE_12),
                        contentPadding = PaddingValues(
                            start = Dimension.SIZE_16,
                            end = Dimension.SIZE_16,
                            top = Dimension.SIZE_2,
                            bottom = Dimension.SIZE_2
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.yes),
                            style = typography.subtitle1,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}