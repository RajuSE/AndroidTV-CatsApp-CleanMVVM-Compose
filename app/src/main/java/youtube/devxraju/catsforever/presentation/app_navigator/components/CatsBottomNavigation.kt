//package youtube.devxraju.catsforever.presentation.app_navigator.components
//
//import android.content.res.Configuration.UI_MODE_NIGHT_YES
//import androidx.annotation.DrawableRes
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.size
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment.Companion.CenterHorizontally
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import youtube.devxraju.catsforever.R
//import youtube.devxraju.catsforever.presentation.Dimens.ExtraSmallPadding2
//import youtube.devxraju.catsforever.presentation.Dimens.IconSize
//import youtube.devxraju.catsforever.theme.CatsAppTheme
//
//@Composable
//fun CatsBottomNavigation(
//    items: List<BottomNavigationItem>,
//    selectedItem: Int,
//    onItemClick: (Int) -> Unit
//) {
//    NavigationBar(
//        modifier = Modifier.fillMaxWidth(),
//        containerColor = colorResource(id = R.color.maincolor),
//        tonalElevation = 10.dp
//    ) {
//        items.forEachIndexed { index, item ->
//            NavigationBarItem(
//                selected = index == selectedItem,
//                onClick = { onItemClick(index) },
//                icon = {
//                    Column(horizontalAlignment = CenterHorizontally) {
//                        Icon(
//                            painter = painterResource(id = item.icon),
//                            contentDescription = null,
//                            modifier = Modifier.size(IconSize),
//                        )
//                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
//                        Text(text = item.text, style = MaterialTheme.typography.labelMedium)
//                    }
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.primary,
//                    selectedTextColor = MaterialTheme.colorScheme.primary,
//                    unselectedIconColor = colorResource(id = R.color.bottombar_text),
//                    unselectedTextColor = colorResource(id = R.color.bottombar_text),
//                    indicatorColor = MaterialTheme.colorScheme.background
//                ),
//            )
//        }
//    }
//}
//
//data class BottomNavigationItem(
//    @DrawableRes val icon: Int,
//    val text: String
//)
//
////@Preview
////@Preview(uiMode = UI_MODE_NIGHT_YES)
////@Composable
////fun CatsBottomNavigationPreview() {
////    CatsAppTheme(dynamicColor = false) {
////        Column {
////            Box(modifier = Modifier.fillMaxWidth().height(0.5.dp).background
////                (color =  colorResource(id = R.color.placeholder)))
////        CatsBottomNavigation(items = listOf(
////            BottomNavigationItem(icon = R.drawable.img_paw, text = "Explore"),
////            BottomNavigationItem(icon = R.drawable.img_fav, text = "Favourites"),
////            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
////        ), selectedItem = 0, onItemClick = {})
////    }}
////}