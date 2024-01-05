package id.belitong.bigs.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GeositeResponse(
    @field:SerializedName("geosites")
    val geosites: List<GeositeItem>
)

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

    @field:SerializedName("plant")
    val plant: String,

    @field:SerializedName("animal")
    val animal: String,

    @field:SerializedName("distance")
    val distance: Int,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("hours")
    val hours: List<String>,

    @field:SerializedName("img")
    val img: String? = null,
)