package id.belitong.bigs.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BiodiversityResponse(

    @field:SerializedName("total_count")
    val amount: Int? = 0,

    @field:SerializedName("items")
    val items: List<BiodiversityItem>? = null
)