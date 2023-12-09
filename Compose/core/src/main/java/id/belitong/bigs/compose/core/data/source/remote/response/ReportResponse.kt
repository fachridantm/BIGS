package id.belitong.bigs.compose.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReportResponse(
	@field:SerializedName("reports")
	val reports: List<ReportItem>
)

data class ReportItem(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("short_desc")
	val shortDesc: String,

	@field:SerializedName("place")
	val place: String,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("status")
	val status: String
)
