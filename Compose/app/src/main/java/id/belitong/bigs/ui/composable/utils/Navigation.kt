package id.belitong.bigs.ui.composable.utils

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction

fun DestinationsNavigator.navigateAndFinish(route: Direction){
    popBackStack()
    navigate(route)
}