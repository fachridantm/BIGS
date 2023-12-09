package id.belitong.bigs.compose.ui.screen.search

import android.content.Context
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.Biodiversity
import id.belitong.bigs.compose.core.utils.DummyData
import id.belitong.bigs.compose.ui.composable.components.ChipGroupSingleSelectionWithDrawableStart
import id.belitong.bigs.compose.ui.composable.components.SearchListItem
import id.belitong.bigs.compose.ui.composable.model.ChipFilter
import id.belitong.bigs.compose.ui.composable.model.getSearchFilter
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val selectedChip = remember { mutableStateOf(ChipFilter.NULL) }

    val biodiversities = DummyData.getAllBiodiversity()
    var chipData by remember { mutableStateOf(biodiversities) }

    chipData = when (selectedChip.value) {
        ChipFilter.GEOSITE -> {
            biodiversities.filter {
                it.type != stringResource(R.string.animal) && it.type != stringResource(R.string.plant)
            }
        }

        ChipFilter.PLANT -> {
            biodiversities.filter { it.type == stringResource(R.string.plant) }
        }

        ChipFilter.ANIMAL -> {
            biodiversities.filter { it.type == stringResource(R.string.animal) }
        }

        ChipFilter.LOCATION -> {
            emptyList()
        }

        else -> {
            emptyList()
        }
    }

    SearchScreenContent(
        modifier = modifier,
        context = context,
        biodiversities = chipData,
        selectedChip = selectedChip,
        onBackPressed = { onBackPressedDispatcher?.onBackPressed() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenContent(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    biodiversities: List<Biodiversity> = emptyList(),
    selectedChip: MutableState<ChipFilter> = remember { mutableStateOf(ChipFilter.NULL) },
    onBackPressed: () -> Unit = {},
    scope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    var query by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimension.SIZE_16),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_12, bottom = Dimension.SIZE_26),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .clickable(
                        onClick = onBackPressed
                    ),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(R.string.back),
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.title_activity_search),
                style = typography.h3,
                color = Color.Black.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimension.SIZE_50),
            value = query,
            onValueChange = { query = it },
            shape = MaterialTheme.shapes.small,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_hint),
                    color = Color.Black.copy(alpha = 0.4f),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            },
            trailingIcon = if (query.isNotEmpty()) {
                {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable { query = "" }
                    )
                }
            } else {
                null
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black.copy(alpha = 0.6f),
                unfocusedBorderColor = Color.Black.copy(alpha = 0.6f),
                disabledBorderColor = Color.Black.copy(alpha = 0.6f),
                cursorColor = Color.Black
            ),
            singleLine = true,
            maxLines = 1,
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = Dimension.SIZE_12),
            text = stringResource(R.string.search_by_category),
            style = typography.body1,
            color = Color.Black,
            textAlign = TextAlign.Start,
        )
        ChipGroupSingleSelectionWithDrawableStart(
            modifier = Modifier.wrapContentSize(),
            listFilter = getSearchFilter(),
            selectedChip = selectedChip.value,
            onChipSelected = {
                selectedChip.value = it
                if (selectedChip.value == ChipFilter.LOCATION) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            context.getString(R.string.on_click_handler)
                        )
                    }
                }
            },
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.SIZE_8),
        ) {
            items(biodiversities) { biodiversity ->
                SearchListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimension.SIZE_8),
                    biodiversity = biodiversity,
                    onItemClicked = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                context.getString(R.string.on_click_handler)
                            )
                        }
                    }
                )
            }
        }
    }
}
