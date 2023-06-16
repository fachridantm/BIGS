package id.belitong.bigs.core.domain.model

data class Plant(
    val id: Int,
    val name: String,
    val latin: String,
    val type: String,
    val desc: String,
    val location: String,
    val img: String,
)