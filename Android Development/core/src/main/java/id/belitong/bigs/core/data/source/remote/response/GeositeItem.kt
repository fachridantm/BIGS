package id.belitong.bigs.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GeositeItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("summary")
    val summary: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("desc")
    val desc: String,

    @field:SerializedName("distance")
    val distance: Int,

    @field:SerializedName("img")
    val img: String,
)


