package id.belitong.bigs.ui.composable.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import id.belitong.bigs.core.data.Resource

inline fun <reified T> getValue(resource: Resource<T>?): T {
    val successResource = resource as Resource.Success
    return successResource.data
}

inline fun <reified T> getErrorMessage(resource: Resource<T>?): String {
    val errorResource = resource as Resource.Error
    return errorResource.errorMessage
}

@Composable
inline fun <reified T> ComposableObserver(
    state: State<Resource<T>?>,
    onLoading: @Composable () -> Unit,
    onSuccess: @Composable (T) -> Unit,
    onFailure: @Composable (String) -> Unit
) {
    when (state.value as Resource<T>) {
        is Resource.Loading -> {
            onLoading.invoke()
        }

        is Resource.Success -> {
            onSuccess.invoke(getValue(state.value))
        }

        is Resource.Error -> {
            onFailure.invoke(getErrorMessage(state.value))
        }

        else -> {}
    }
}