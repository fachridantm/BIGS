package id.belitong.bigs.ui.composable.utils

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getActivity(): Activity = LocalContext.current as ComponentActivity