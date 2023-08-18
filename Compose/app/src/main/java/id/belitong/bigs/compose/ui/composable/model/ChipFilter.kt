package id.belitong.bigs.compose.ui.composable.model

import androidx.compose.ui.graphics.Color
import id.belitong.bigs.compose.R
import id.belitong.bigs.compose.ui.theme.md_theme_dark_primaryVariant
import id.belitong.bigs.compose.ui.theme.md_theme_dark_secondaryVariant
import id.belitong.bigs.compose.ui.theme.md_theme_light_error
import id.belitong.bigs.compose.ui.theme.senary

enum class ChipFilter(val value: String) {
    NULL(""),
    ALL("All"),
    GEOSITE("Geosites"),
    ANIMAL("Animals"),
    PLANT("Plants"),
    ALPHABET("A - Z"),
    NEAREST("Nearest"),
    LOCATION("Location"),
}

data class ChipFilterWithIcon(
    val chipFilter: ChipFilter,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val selectedIconTint: Color,
    val unselectedIconTint: Color,
)

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

fun getSearchFilter(): List<ChipFilterWithIcon> {
    return listOf(
        ChipFilterWithIcon(
            chipFilter = ChipFilter.GEOSITE,
            selectedIcon = R.drawable.ic_geosites,
            unselectedIcon = R.drawable.ic_geosites,
            selectedIconTint = Color.White,
            unselectedIconTint = md_theme_dark_primaryVariant,
        ),
        ChipFilterWithIcon(
            chipFilter = ChipFilter.PLANT,
            selectedIcon = R.drawable.ic_plant,
            unselectedIcon = R.drawable.ic_plant,
            selectedIconTint = Color.White,
            unselectedIconTint = md_theme_dark_secondaryVariant,
        ),
        ChipFilterWithIcon(
            chipFilter = ChipFilter.ANIMAL,
            selectedIcon = R.drawable.ic_animals,
            unselectedIcon = R.drawable.ic_animals,
            selectedIconTint = Color.White,
            unselectedIconTint = senary,
        ),
        ChipFilterWithIcon(
            chipFilter = ChipFilter.LOCATION,
            selectedIcon = R.drawable.ic_distance,
            unselectedIcon = R.drawable.ic_distance,
            selectedIconTint = Color.White,
            unselectedIconTint = md_theme_light_error,
        ),
    )
}