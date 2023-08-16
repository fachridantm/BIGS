package id.belitong.bigs.compose.ui.composable.model

enum class ChipFilter(val value: String) {
    NULL(""),
    ALL("All"),
    GEOSITE("Geosites"),
    ANIMAL("Animals"),
    PLANT("Plants"),
    ALPHABET("A - Z"),
    NEAREST("Nearest"),
}

fun getBiodiversityFilter(): List<ChipFilter> {
    return listOf(
        ChipFilter.ALL,
        ChipFilter.GEOSITE,
        ChipFilter.ANIMAL,
        ChipFilter.PLANT,
    )
}

fun getGeositesFilter(): List<ChipFilter> {
    return listOf(
        ChipFilter.ALPHABET,
        ChipFilter.NEAREST,
    )
}