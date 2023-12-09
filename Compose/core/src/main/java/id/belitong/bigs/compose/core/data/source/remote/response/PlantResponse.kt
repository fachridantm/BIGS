package id.belitong.bigs.compose.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PlantResponse(

	@field:SerializedName("plants")
	val plants: List<PlantItem>
)

data class PlantItem(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("latin")
	val latin: String
)
