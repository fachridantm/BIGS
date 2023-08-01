package id.belitong.bigs.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GeositeResponse(

    @field:SerializedName("total_count")
    val amount: Int? = 0,

    @field:SerializedName("items")
    val items: List<GeositeItem>? = null
)