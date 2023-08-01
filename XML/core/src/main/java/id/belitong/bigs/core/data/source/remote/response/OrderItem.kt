package id.belitong.bigs.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class OrderItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("geosite_name")
    val geositeName: String,

    @field:SerializedName("geosite_image")
    val geositeImage: String,

    @field:SerializedName("tour_guide_name")
    val tourGuideName: String,

    @field:SerializedName("tour_guide_phone")
    val tourGuidePhone: String,

    @field:SerializedName("booking_date")
    val bookingDate: String,

    @field:SerializedName("tour_date")
    val tourDate: String,

    @field:SerializedName("program_name")
    val programName: String,

    @field:SerializedName("status")
    val status: String,
)