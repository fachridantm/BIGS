package id.belitong.bigs.compose.core.domain.model

data class Order(
    val id: Int,
    val geositeName: String,
    val geositeImage: String,
    val tourGuideName: String,
    val bookingDate: String,
    val bookingTime: String,
    val tourDate: String,
    val phoneNumber: String,
    val program: String,
    val status: String,
    val instance: String,
)