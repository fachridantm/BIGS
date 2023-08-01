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

fun getBiodiversityFilter(value: String): BiodiversityFilter {
    return when (value) {
        BiodiversityFilter.ALL.value -> BiodiversityFilter.ALL
        BiodiversityFilter.GEOSITE.value -> BiodiversityFilter.GEOSITE
        BiodiversityFilter.ANIMAL.value -> BiodiversityFilter.ANIMAL
        BiodiversityFilter.PLANT.value -> BiodiversityFilter.PLANT
        else -> BiodiversityFilter.ALL
    }
}