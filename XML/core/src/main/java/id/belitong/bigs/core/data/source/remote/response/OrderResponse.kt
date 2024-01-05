package id.belitong.bigs.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @field:SerializedName("orders")
    val orders: List<OrderItem>
)

data class OrderItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("geositeName")
    val geositeName: String,

    @field:SerializedName("geositeImage")
    val geositeImage: String? = null,

    @field:SerializedName("tourGuideName")
    val tourGuideName: String,

    @field:SerializedName("bookingDate")
    val bookingDate: String,

    @field:SerializedName("bookingTime")
    val bookingTime: String,

    @field:SerializedName("tourDate")
    val tourDate: String,

    @field:SerializedName("phoneNumber")
    val phoneNumber: String,

    @field:SerializedName("program")
    val program: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("instance")
    val instance: String,
)