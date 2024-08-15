package youtube.devxraju.catsforever.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import youtube.devxraju.catsforever.presentation.Dimens.ExtraSmallPadding6
import youtube.devxraju.catsforever.presentation.Dimens.MediumPadding1
import youtube.devxraju.catsforever.presentation.Dimens.MediumPadding2
import youtube.devxraju.catsforever.presentation.Dimens.SmallPadding12
import youtube.devxraju.catsforever.presentation.common.CatsList


@Composable
fun HomeScreen(
    cats: LazyPagingItems<CatBreedsResponseItem>,
    navigateToDetails: (CatBreedsResponseItem) -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding2, start = MediumPadding1, end = MediumPadding1)
    ) {
        Spacer(modifier = Modifier.height(ExtraSmallPadding6))

        CatsList(
            cats = cats,
            onClick = navigateToDetails
        )
    }
}