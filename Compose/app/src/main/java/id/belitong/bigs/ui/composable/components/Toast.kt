package id.belitong.bigs.ui.composable.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Toast(message: String) {
    var showToast by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(showToast) {
        if (showToast) {
            delay(2000) // Display toast for 2 seconds
            showToast = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (showToast) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

    coroutineScope.launch {
        showToast = true
    }
}
