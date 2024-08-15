package youtube.devxraju.catsforever.presentation.details

import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem

sealed class DetailsEvent {
    data class UpsertDeleteCat(val cat: CatBreedsResponseItem) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}