package youtube.devxraju.catsforever.presentation.common.topbar

import youtube.devxraju.catsforever.R

enum class Screens(
    private val args: List<String>? = null,
    val isTabItem: Boolean = false,
    var sName:String = "Explore",
    var url:String = "",
    var img:Int = 0
) {
    Home(isTabItem = true,sName="Explore", img = R.drawable.img_paw),
    Favourites(isTabItem = true, sName = "Favourites",img = R.drawable.img_fav),
    Search(isTabItem = true, sName = "Search",img = R.drawable.ic_search),
    Main(isTabItem = false),
    Details(isTabItem = false),
    WebView(isTabItem = false, url="");


    operator fun invoke(): String {
        val argList = StringBuilder()
        args?.let { nnArgs ->
            nnArgs.forEach { arg -> argList.append("/{$arg}") }
        }
        return name + argList
    }


}
