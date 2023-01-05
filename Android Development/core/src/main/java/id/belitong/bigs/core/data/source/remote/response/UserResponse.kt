package id.belitong.bigs.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("phone_number")
    val phoneNumber: String,
)