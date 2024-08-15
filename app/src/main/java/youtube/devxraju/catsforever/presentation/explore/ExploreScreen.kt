package youtube.devxraju.catsforever.presentation.explore

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import youtube.devxraju.catsforever.presentation.app_navigator.OnBackClickStateSaver
import youtube.devxraju.catsforever.presentation.app_navigator.ParentPadding
import youtube.devxraju.catsforever.presentation.common.topbar.DashboardTopBar
import youtube.devxraju.catsforever.presentation.common.topbar.Screens
import youtube.devxraju.catsforever.presentation.common.topbar.TopBarFocusRequesters
import youtube.devxraju.catsforever.presentation.common.topbar.TopBarTabs
import youtube.devxraju.catsforever.presentation.favourites.FavouritesScreen
import youtube.devxraju.catsforever.presentation.favourites.FavouritesViewModel
import youtube.devxraju.catsforever.presentation.home.HomeScreen
import youtube.devxraju.catsforever.presentation.home.HomeViewModel
import youtube.devxraju.catsforever.presentation.search.SearchRoute
import youtube.devxraju.catsforever.presentation.search.SearchViewModel

interface OnItemClicked{
    fun onItemClick(cat:CatBreedsResponseItem)
}
@Composable
fun ExploreScreen(onBackPressed: () -> Unit, onItemClicked: OnItemClicked) {
    val density = LocalDensity.current
    val focusManager = LocalFocusManager.current
    val navController = rememberNavController()

    var isTopBarVisible by remember { mutableStateOf(true) }
    var isTopBarFocused by remember { mutableStateOf(false) }

    var currentDestination: String? by remember { mutableStateOf(null) }
    val currentTopBarSelectedTabIndex by remember(currentDestination) {
        derivedStateOf {
            currentDestination?.let { TopBarTabs.indexOf(Screens.valueOf(it)) } ?: 0
        }
    }
    // We do not want to focus the TopBar everytime we come back from another screen e.g.
    // MovieDetails, CategoryMovieList or VideoPlayer screen
    var wasTopBarFocusRequestedBefore by rememberSaveable { mutableStateOf(false) }

    var topBarHeightPx: Int by rememberSaveable { mutableIntStateOf(0) }

    // Used to show/hide DashboardTopBar
    val topBarYOffsetPx by animateIntAsState(
        targetValue = if (isTopBarVisible) 0 else -topBarHeightPx,
        animationSpec = tween(),
        label = "",
        finishedListener = {
            if (it == -topBarHeightPx) {
                focusManager.moveFocus(FocusDirection.Down)
            }
        }
    )

    // Used to push down/pull up NavHost when DashboardTopBar is shown/hidden
    val navHostTopPaddingDp by animateDpAsState(
        targetValue = if (isTopBarVisible) with(density) { topBarHeightPx.toDp() } else 0.dp,
        animationSpec = tween(),
        label = "",
    )

    DisposableEffect(Unit) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            currentDestination = destination.route
            println("currentDestination:$currentDestination")
        }


        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
    println("currentTopBarSelectedTabIndex:$currentTopBarSelectedTabIndex")

    BackPressHandledArea(
        // 1. On user's first back press, bring focus to the current selected tab, if TopBar is not
        //    visible, first make it visible, then focus the selected tab
        // 2. On second back press, bring focus back to the first displayed tab
        // 3. On third back press, exit the app
        onBackPressed = {
            if (!isTopBarVisible) {
                isTopBarVisible = true
                TopBarFocusRequesters[currentTopBarSelectedTabIndex + 1].requestFocus()
            } else if (currentTopBarSelectedTabIndex == 0) onBackPressed()
            else if (!isTopBarFocused) {
                TopBarFocusRequesters[currentTopBarSelectedTabIndex + 1].requestFocus()
            } else TopBarFocusRequesters[1].requestFocus()
        }
    ) {
        // We do not want to focus the TopBar everytime we come back from another screen e.g.
        // MovieDetails, CategoryMovieList or VideoPlayer screen
        var wasTopBarFocusRequestedBefore by rememberSaveable { mutableStateOf(false) }



        LaunchedEffect(Unit) {
            if (!wasTopBarFocusRequestedBefore) {
                TopBarFocusRequesters[currentTopBarSelectedTabIndex + 1].requestFocus()
                wasTopBarFocusRequestedBefore = true
            }
        }

        DashboardTopBar(
            modifier = Modifier
                .offset { IntOffset(x = 0, y = topBarYOffsetPx) }
                .onSizeChanged { topBarHeightPx = it.height }
                .onFocusChanged { isTopBarFocused = it.hasFocus }
                .padding(
                    top = ParentPadding.calculateTopPadding(),
                    start = 28.dp
                ),
            selectedTabIndex = currentTopBarSelectedTabIndex,
        ) { screen ->
            println("Screen:${screen.name}")
            navigateToTab(
                navController = navController,
                route = screen.name
            )
//            navController.navigate(screen()) {
//                if (screen == TopBarTabs[0]) popUpTo(TopBarTabs[0].invoke())
//                launchSingleTop = true
//            }
        }

        Body(
            navController,
            onItemClicked,
            modifier = Modifier.offset(y = navHostTopPaddingDp),
        )

    }
}

@Composable
fun Body(
    navController: NavHostController,
    onItemClicked: OnItemClicked,
    modifier: Modifier
) {
    NavHost(
        modifier = modifier.padding(start = 30.dp, end = 30.dp),
        navController = navController,
        startDestination = Screens.Home(),
    ) {
        composable(Screens.Home()) {
            val viewModel: HomeViewModel = hiltViewModel()
            val cats = viewModel.catsData.collectAsLazyPagingItems()
            HomeScreen(
                cats = cats,
                navigateToDetails = { cat ->
                     onItemClicked.onItemClick(cat)

//                    commonViewModel.viewModelScope.launch {
//                        commonViewModel.onCatClicked(cat)
//                        navigateToDetails(navController = navController)
//                    }
                }
            )
        }
        composable(Screens.Favourites()) {
            val viewModel: FavouritesViewModel = hiltViewModel()
            val state = viewModel.state.value
            OnBackClickStateSaver(navController = navController)
            FavouritesScreen(
                state = state,
                navigateToDetails = { cat ->
                    onItemClicked.onItemClick(cat)
//                    commonViewModel.viewModelScope.launch {
//                        commonViewModel.onCatClicked(cat)
//                        navigateToDetails(navController = navController)
//                    }
                }
            )
        }
        composable(Screens.Search()) {
            val viewModel: SearchViewModel = hiltViewModel()
            val state = viewModel.state.value
            OnBackClickStateSaver(navController = navController)
            SearchRoute(
                state = state,
                event = viewModel::onEvent,
                navigateToDetails = { cat ->
                    onItemClicked.onItemClick(cat)
//                    commonViewModel.viewModelScope.launch {
//                        commonViewModel.onCatClicked(cat)
//                        navigateToDetails(navController = navController)
//                    }
                }
            )
        }
    }
}



@Composable
private fun BackPressHandledArea(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) =
    Box(
        modifier = Modifier
            .onPreviewKeyEvent {
                if (it.key == Key.Back && it.type == KeyEventType.KeyUp) {
                    onBackPressed()
                    true
                } else {
                    false
                }
            }
            .then(modifier),
        content = content
    )

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