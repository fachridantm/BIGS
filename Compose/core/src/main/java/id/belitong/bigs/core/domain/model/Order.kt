package id.belitong.bigs.core.domain.model

data class Order(
    val id: Int,
    val geositeName: String,
    val geositeImage: String,
    val tourGuideName: String,
    val tourGuidePhone: String,
    val bookingDate: String,
    val tourDate: String,
    val programName: String,
    val status: String,
)