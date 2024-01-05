package id.belitong.bigs.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PlantResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("latin")
    val latin: String
)