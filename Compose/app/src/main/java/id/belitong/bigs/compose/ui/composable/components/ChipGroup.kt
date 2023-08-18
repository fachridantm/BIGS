package id.belitong.bigs.compose.ui.composable.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import id.belitong.bigs.compose.ui.composable.model.ChipFilter
import id.belitong.bigs.compose.ui.composable.model.ChipFilterWithIcon
import id.belitong.bigs.compose.ui.composable.model.getBiodiversityFilter
import id.belitong.bigs.compose.ui.composable.model.getSearchFilter
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.seed
import id.belitong.bigs.compose.ui.theme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipGroupSingleSelection(
    modifier: Modifier = Modifier,
    listFilter: List<ChipFilter> = getBiodiversityFilter(),
    selectedChip: ChipFilter = ChipFilter.ALL,
    onChipSelected: (ChipFilter) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimension.SIZE_6),
    ) {
        items(listFilter) {
            FilterChip(
                selected = selectedChip == it,
                onClick = {
                    onChipSelected(it)
                },
                label = {
                    Text(
                        text = it.value,
                        style = typography.subtitle1
                    )
                },
                leadingIcon = if (selectedChip == it) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = it.value,
                            modifier = Modifier.size(FilterChipDefaults.IconSize),
                            tint = Color.White
                        )
                    }
                } else {
                    null
                },
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = seed,
                    disabledSelectedBorderColor = seed,
                    borderWidth = Dimension.SIZE_1,
                    selectedBorderWidth = Dimension.SIZE_1,
                ),
                shape = RoundedCornerShape(Dimension.SIZE_30),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = seed,
                    disabledContainerColor = Color.White,
                    selectedLabelColor = Color.White,
                    disabledLabelColor = seed,
                ),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ChipGroupSingleSelectionWithDrawableStart(
    modifier: Modifier = Modifier,
    listFilter: List<ChipFilterWithIcon> = getSearchFilter(),
    selectedChip: ChipFilter = ChipFilter.NULL,
    onChipSelected: (ChipFilter) -> Unit,
) {
    FlowRow(
        modifier = modifier,
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.spacedBy(Dimension.SIZE_10),
    ) {
        listFilter.forEach {
            FilterChip(
                modifier = Modifier.wrapContentSize(),
                selected = selectedChip == it.chipFilter,
                onClick = {
                    onChipSelected(it.chipFilter)
                },
                label = {
                    Text(
                        text = it.chipFilter.value,
                        style = typography.subtitle1
                    )
                },
                leadingIcon = if (selectedChip == it.chipFilter) {
                    {
                        Icon(
                            painter = painterResource(id = it.selectedIcon),
                            contentDescription = it.chipFilter.value,
                            modifier = Modifier.size(FilterChipDefaults.IconSize),
                            tint = it.selectedIconTint,
                        )
                    }
                } else {
                    {
                        Icon(
                            painter = painterResource(id = it.unselectedIcon),
                            contentDescription = it.chipFilter.value,
                            modifier = Modifier.size(FilterChipDefaults.IconSize),
                            tint = it.unselectedIconTint,
                        )
                    }
                },
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = seed,
                    disabledSelectedBorderColor = seed,
                    borderWidth = Dimension.SIZE_1,
                    selectedBorderWidth = Dimension.SIZE_1,
                ),
                shape = RoundedCornerShape(Dimension.SIZE_30),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = seed,
                    disabledContainerColor = Color.White,
                    selectedLabelColor = Color.White,
                    disabledLabelColor = seed,
                    selectedLeadingIconColor = it.selectedIconTint,
                    disabledLeadingIconColor = it.unselectedIconTint,
                ),
            )
        }
    }
}