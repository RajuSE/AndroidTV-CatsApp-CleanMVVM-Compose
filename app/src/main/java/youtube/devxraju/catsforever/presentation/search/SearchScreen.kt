package youtube.devxraju.catsforever.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import youtube.devxraju.catsforever.presentation.Dimens.MediumPadding1
import youtube.devxraju.catsforever.presentation.common.CatsList
import youtube.devxraju.catsforever.presentation.common.SearchBar

@Composable
fun SearchRoute(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigateToDetails:(CatBreedsResponseItem) -> Unit
) {
    println("SearchRoute fun")

    Column(
        modifier = Modifier
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(SearchEvent.SearchCats)
            }
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.cats?.let {
            val cats = it.collectAsLazyPagingItems()
            CatsList(
                cats = cats,
                onClick = navigateToDetails
            )
        }
    }
}