package youtube.devxraju.catsforever.util

sealed class DataState {

    data class FavoriteUnfav(val isFavorited: Boolean) : DataState()

}

sealed class UIComponent {

    data class Toast(val message: String): UIComponent()

    data class Dialog(val title: String, val message: String): UIComponent()

}