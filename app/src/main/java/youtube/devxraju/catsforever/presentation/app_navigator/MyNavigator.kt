package youtube.devxraju.catsforever.presentation.app_navigator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.tv.material3.ExperimentalTvMaterial3Api
import kotlinx.coroutines.launch
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import youtube.devxraju.catsforever.presentation.common.CommonViewModel
import youtube.devxraju.catsforever.presentation.common.topbar.Screens
import youtube.devxraju.catsforever.presentation.common.webview.WebView
import youtube.devxraju.catsforever.presentation.details.DetailsScreen
import youtube.devxraju.catsforever.presentation.details.DetailsViewModel
import youtube.devxraju.catsforever.presentation.explore.ExploreScreen
import youtube.devxraju.catsforever.presentation.explore.OnItemClicked
import youtube.devxraju.catsforever.presentation.navgraph.Route

val ParentPadding = PaddingValues(vertical = 16.dp, horizontal = 58.dp)


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppNavigator(onBackPressed: () -> Unit) {


    //----
    val navController = rememberNavController()
    val commonViewModel: CommonViewModel = hiltViewModel()



    NavHost(
        navController = navController,
        startDestination = Screens.Main()
    ) {

        composable(route = Screens.Main()) {

                ExploreScreen(onBackPressed, onItemClicked = object: OnItemClicked{
                    override fun onItemClick(cat: CatBreedsResponseItem) {
                        commonViewModel.viewModelScope.launch {
                            commonViewModel.onCatClicked(cat)
                            navigateToDetails(navController)
                        }

                    }

                })



        }

        composable(route = Screens.Details()) {
            val viewModel: DetailsViewModel = hiltViewModel()
            commonViewModel.currentCat?.let { cat ->
                DetailsScreen(
                    cat = cat,
                    event = viewModel::onEvent,
                    navigateUp = {
                        navController.navigateUp()
                    },
                    isFavoritedAlready = commonViewModel.isCurrentCatFav,
                    favUnfav = viewModel.favoriteUnfav,
                    openWebView = {
                        commonViewModel.onWebViewClicked(it)
                        navController.navigate(Screens.WebView())
                    }
                )
            }
        }

        composable(route = Screens.WebView()){
            commonViewModel.webviewURL?.let {
                WebView(url = commonViewModel.webviewURL!!,
                    navigateUp = {
                        navController.navigateUp()
                    })
            }
        }
    }

}


@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeRoute.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}


private fun navigateToDetails(navController: NavController) {

    navController.navigate(
        Screens.Details()
    )
}

