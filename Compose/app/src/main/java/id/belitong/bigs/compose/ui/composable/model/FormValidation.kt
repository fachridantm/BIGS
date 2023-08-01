package id.belitong.bigs.compose.ui.composable.model

import id.belitong.bigs.compose.core.utils.emptyString

data class FormValidation(
    var text : String = emptyString(),
    var isError : Boolean = false,
    val errorMessage : String = emptyString(),
    val hint : String = emptyString()
)