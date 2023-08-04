package id.belitong.bigs.compose.ui.composable.model

enum class BiodiversityFilter(val value: String) {
    ALL("All"),
    GEOSITE("Geosites"),
    ANIMAL("Animals"),
    PLANT("Plants"),
}

fun getAllBiodiversityFilter(): List<BiodiversityFilter> {
    return listOf(
        BiodiversityFilter.ALL,
        BiodiversityFilter.GEOSITE,
        BiodiversityFilter.ANIMAL,
        BiodiversityFilter.PLANT,
    )
}