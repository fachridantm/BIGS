package id.belitong.bigs.compose.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BiodiversityResponse(
    @field:SerializedName("biodiversities")
    val biodiversities: List<BiodiversityItem>
)

data class BiodiversityItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("img")
    val img: String? = null,
)