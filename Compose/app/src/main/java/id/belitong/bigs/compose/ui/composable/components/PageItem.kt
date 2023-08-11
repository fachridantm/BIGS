package id.belitong.bigs.compose.ui.composable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.Biodiversity
import id.belitong.bigs.compose.core.domain.model.Geosite
import id.belitong.bigs.compose.core.domain.model.Order
import id.belitong.bigs.compose.core.utils.HistoryStatus
import id.belitong.bigs.compose.core.utils.getFirstTwoWords
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.TextSize
import id.belitong.bigs.compose.ui.theme.md_theme_dark_secondary
import id.belitong.bigs.compose.ui.theme.md_theme_dark_tertiary
import id.belitong.bigs.compose.ui.theme.md_theme_light_error
import id.belitong.bigs.compose.ui.theme.md_theme_light_primary
import id.belitong.bigs.compose.ui.theme.typography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CarouselItem(
    modifier: Modifier = Modifier,
    page: Int = 0,
    geosites: List<Geosite> = emptyList(),
    onItemClicked: (Geosite) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(Dimension.SIZE_12))
            .clickable { onItemClicked(geosites[page]) },
        elevation = CardDefaults.cardElevation(defaultElevation = Dimension.SIZE_4)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                model = geosites[page].img,
                contentDescription = geosites[page].name,
                contentScale = ContentScale.Crop,
            ) {
                it.placeholder(R.drawable.img_placeholder_geopark)
            }
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = Dimension.SIZE_16, bottom = Dimension.SIZE_16)
                    .alpha(0.8f)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(Dimension.SIZE_20),
                    )
                    .padding(horizontal = Dimension.SIZE_28, vertical = Dimension.SIZE_6),
                text = geosites[page].name,
                textAlign = TextAlign.Center,
                color = Color.White,
                style = typography.h4.copy(fontSize = TextSize.SIZE_14),
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeGridItem(
    modifier: Modifier = Modifier,
    biodiversity: Biodiversity,
    onItemClicked: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(120.dp)
            .height(155.dp)
            .clip(RoundedCornerShape(Dimension.SIZE_12))
            .clickable(
                onClick = onItemClicked
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimension.SIZE_4),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                model = biodiversity.img,
                contentDescription = biodiversity.name,
                contentScale = ContentScale.Crop,
            ) {
                it.placeholder(R.drawable.img_placeholder_geopark)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = Dimension.SIZE_10),
                    text = biodiversity.name.getFirstTwoWords(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    color = md_theme_dark_tertiary,
                    style = typography.body1,
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = Dimension.SIZE_12),
                    text = biodiversity.type,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    color = Color.White,
                    style = typography.subtitle1,
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HistoryGridItem(
    modifier: Modifier = Modifier,
    order: Order,
    onItemClicked: (Order) -> Unit
) {
    val invisible = if (
        order.status == HistoryStatus.COMPLETED.value ||
        order.status == HistoryStatus.CANCELLED.value
    ) 0f else 1f

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = Dimension.SIZE_8,
                shape = RoundedCornerShape(Dimension.SIZE_12),
                clip = true
            ),
        elevation = CardDefaults.cardElevation(Dimension.SIZE_8),
        shape = RoundedCornerShape(Dimension.SIZE_12),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimension.SIZE_16),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = order.geositeName,
                        style = typography.h4,
                        color = Color.Black,
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = Dimension.SIZE_12),
                        text = stringResource(id = R.string.tour_guide_name),
                        style = typography.subtitle1,
                        color = Color.Black.copy(alpha = 0.4f),
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = order.tourGuideName,
                        style = typography.subtitle1,
                        color = Color.Black,
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = Dimension.SIZE_12),
                        text = stringResource(id = R.string.booking_date),
                        style = typography.subtitle1,
                        color = Color.Black.copy(alpha = 0.4f),
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = order.bookingDate,
                        style = typography.subtitle1,
                        color = Color.Black,
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = Dimension.SIZE_12),
                        text = stringResource(id = R.string.tour_date),
                        style = typography.subtitle1,
                        color = Color.Black.copy(alpha = 0.4f),
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = order.tourDate,
                        style = typography.subtitle1,
                        color = Color.Black,
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = Dimension.SIZE_12),
                        text = stringResource(id = R.string.contact),
                        style = typography.subtitle1,
                        color = Color.Black.copy(alpha = 0.4f),
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = order.tourGuidePhone,
                        style = typography.subtitle1,
                        color = Color.Black,
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = Dimension.SIZE_12),
                        text = stringResource(id = R.string.program),
                        style = typography.subtitle1,
                        color = Color.Black.copy(alpha = 0.4f),
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = order.programName,
                        style = typography.subtitle1,
                        color = Color.Black,
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = Dimension.SIZE_12),
                        text = stringResource(id = R.string.status),
                        style = typography.subtitle1,
                        color = Color.Black.copy(alpha = 0.4f),
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(
                                color = when (order.status) {
                                    HistoryStatus.ACCEPTED.value, HistoryStatus.COMPLETED.value -> md_theme_light_primary
                                    HistoryStatus.REJECTED.value, HistoryStatus.CANCELLED.value -> md_theme_light_error
                                    else -> md_theme_dark_secondary
                                },
                                shape = RoundedCornerShape(Dimension.SIZE_20)
                            )
                            .padding(vertical = Dimension.SIZE_4, horizontal = Dimension.SIZE_20),
                        text = order.status,
                        style = typography.subtitle1,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(start = Dimension.SIZE_4, top = Dimension.SIZE_48),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    GlideImage(
                        modifier = Modifier
                            .width(130.dp)
                            .height(130.dp)
                            .clip(MaterialTheme.shapes.medium),
                        model = order.geositeImage,
                        contentDescription = order.geositeName,
                        contentScale = ContentScale.Crop,
                    ) {
                        it.placeholder(R.drawable.img_placeholder_geopark)
                    }
                    Button(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = Dimension.SIZE_16)
                            .alpha(invisible),
                        onClick = { onItemClicked(order) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = md_theme_light_error,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(Dimension.SIZE_12),
                        contentPadding = PaddingValues(
                            start = Dimension.SIZE_20,
                            end = Dimension.SIZE_20,
                            top = Dimension.SIZE_2,
                            bottom = Dimension.SIZE_2
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            style = typography.body2,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}