package id.belitong.bigs.core.domain.model

import id.belitong.bigs.core.utils.emptyString

data class FormValidation(
    var text : String = emptyString(),
    var isError : Boolean = false,
    val errorMessage : String = emptyString(),
    val hint : String = emptyString()
)