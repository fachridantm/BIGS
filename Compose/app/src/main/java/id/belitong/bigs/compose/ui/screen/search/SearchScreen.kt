package id.belitong.bigs.compose.ui.screen.search

import android.content.Context
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.core.domain.model.Biodiversity
import id.belitong.bigs.compose.core.utils.showToast
import id.belitong.bigs.compose.ui.composable.components.BasicLottieAnimation
import id.belitong.bigs.compose.ui.composable.components.ChipGroupSingleSelectionWithDrawableStart
import id.belitong.bigs.compose.ui.composable.components.SearchListItem
import id.belitong.bigs.compose.ui.composable.model.ChipFilter
import id.belitong.bigs.compose.ui.composable.model.getSearchFilter
import id.belitong.bigs.compose.ui.composable.utils.ComposableObserver
import id.belitong.bigs.compose.ui.screen.main.MainViewModel
import id.belitong.bigs.compose.ui.theme.Dimension
import id.belitong.bigs.compose.ui.theme.typography

@Composable
fun SearchScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val biodiversitiesState = mainViewModel.biodiversities.observeAsState()

    val isLoading = remember { mutableStateOf(false) }

    val biodiversities = remember { mutableStateOf(emptyList<Biodiversity>()) }

    val selectedChip = remember { mutableStateOf(ChipFilter.NULL) }
    var chipData by remember { biodiversities }

    LaunchedEffect(key1 = Unit) {
        mainViewModel.getBiodiversities()
    }

    ComposableObserver(
        state = biodiversitiesState,
        onLoading = { isLoading.value = true },
        onSuccess = {
            isLoading.value = false
            biodiversities.value = it
        },
        onError = { message ->
            isLoading.value = false
            message.showToast(context)
        }
    )

    chipData = when (selectedChip.value) {
        ChipFilter.GEOSITE -> {
            biodiversities.value.filter {
                it.type != stringResource(R.string.animal) && it.type != stringResource(R.string.plant)
            }
        }

        ChipFilter.PLANT -> {
            biodiversities.value.filter { it.type == stringResource(R.string.plant) }
        }

        ChipFilter.ANIMAL -> {
            biodiversities.value.filter { it.type == stringResource(R.string.animal) }
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
        isLoading = isLoading.value,
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
    isLoading: Boolean = false,
) {
    var query by remember { mutableStateOf("") }
    val visibility = if (isLoading) 1f else 0f

    Box {
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
                        context.getString(R.string.on_click_handler).showToast(context)
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
                            context.getString(R.string.on_click_handler).showToast(context)
                        }
                    )
                }
            }
        }
        BasicLottieAnimation(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
                .alpha(visibility),
            resId = R.raw.loading,
        )
    }
}
