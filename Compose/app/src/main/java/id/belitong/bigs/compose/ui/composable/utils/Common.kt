package id.belitong.bigs.compose.ui.composable.utils

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable fun getActivity(): Activity = LocalContext.current as ComponentActivity
@Composable fun getContext(): Context = LocalContext.current