package id.belitong.bigs.compose.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("errors")
    val error: Boolean
)
