package youtube.devxraju.catsforever.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeRoute : Route(route = "HomeRoute")

    object SearchRoute : Route(route = "SearchRoute")

    object FavouriteRoute : Route(route = "FavouriteRoute")

    object DetailsRoute : Route(route = "DetailsRoute")

//---------------------------------------------------
    object startNav : Route(route = "startNav")

    object splashToHomeNav : Route(route = "splashToHomeNav")
}

