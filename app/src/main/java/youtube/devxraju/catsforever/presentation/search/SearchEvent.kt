package youtube.devxraju.catsforever.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()

    object SearchCats : SearchEvent()
}