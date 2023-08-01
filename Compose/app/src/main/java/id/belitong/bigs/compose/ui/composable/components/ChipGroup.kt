package id.belitong.bigs.compose.ui.composable.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.ui.composable.model.BiodiversityFilter
import id.belitong.bigs.compose.ui.composable.model.getAllBiodiversityFilter
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.seed
import id.belitong.bigs.compose.ui.theme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipGroupSingleSelection(
    modifier: Modifier = Modifier,
    biodiversityFilter: List<BiodiversityFilter> = getAllBiodiversityFilter(),
    selectedChip: BiodiversityFilter? = BiodiversityFilter.ALL,
    onChipSelected: (String) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimension.SIZE_6),
    ) {
        items(biodiversityFilter) {
            FilterChip(
                selected = selectedChip == it,
                onClick = {
                    onChipSelected(it.value)
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
                            contentDescription = stringResource(R.string.all),
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