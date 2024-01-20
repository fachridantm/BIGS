package id.belitong.bigs.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geosite(
    val id: Int,
    val name: String,
    val summary: String,
    val type: String,
    val desc: String,
    val plant: String,
    val animal: String,
    val distance: Int,
    val location: String,
    val hours: List<String>,
    val img: String,
) : Parcelable
