package id.belitong.bigs.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BiodiversityItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("img")
    val img: String,
)