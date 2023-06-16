package id.belitong.bigs.core.domain.model

data class Geosite(
    val id: Int,
    val name: String,
    val summary: String,
    val type: String,
    val desc: String,
    val distance: Int,
    val img: String,
)
