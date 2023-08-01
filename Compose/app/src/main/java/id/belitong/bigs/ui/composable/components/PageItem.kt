package id.belitong.bigs.ui.composable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import id.belitong.bigs.R
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.utils.getFirstTwoWords
import id.belitong.bigs.ui.theme.Dimension
import id.belitong.bigs.ui.theme.TextSize
import id.belitong.bigs.ui.theme.md_theme_dark_tertiary
import id.belitong.bigs.ui.theme.typography

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