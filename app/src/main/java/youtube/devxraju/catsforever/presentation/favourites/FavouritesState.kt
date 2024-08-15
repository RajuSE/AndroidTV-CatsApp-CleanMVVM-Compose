package youtube.devxraju.catsforever.presentation.favourites

import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem

data class FavouritesState(
    val cats: List<CatBreedsResponseItem> = emptyList()
)