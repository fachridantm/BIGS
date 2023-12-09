package id.belitong.bigs.compose.core.domain.model

data class Report(
    val id: Int,
    val category: String,
    val name: String,
    val shortDesc: String,
    val place: String,
    val photo: String,
    val status: String,
)