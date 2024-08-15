//package youtube.devxraju.catsforever.presentation.navgraph
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navigation
//import youtube.devxraju.catsforever.presentation.app_navigator.AppNavigator
//
//@Composable
//fun NavGraph(
//    startDestination: String
//) {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = startDestination) {
//
//        navigation(
//            route = Route.startNav.route,
//            startDestination = Route.splashToHomeNav.route
//        ) {
//            composable(route = Route.splashToHomeNav.route){
//                AppNavigator()
//            }
//
//        }
//    }
//}