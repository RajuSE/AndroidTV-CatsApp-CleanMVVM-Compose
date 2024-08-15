package youtube.devxraju.catsforever.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import youtube.devxraju.catsforever.presentation.Dimens.ExtraSmallPadding
import youtube.devxraju.catsforever.presentation.Dimens.MediumPadding1
import youtube.devxraju.catsforever.presentation.home.CatCard

@Composable
fun CatsListFavourites(
    modifier: Modifier = Modifier,
    cats: List<CatBreedsResponseItem>,
    onClick: (CatBreedsResponseItem) -> Unit
) {
    if (cats.isEmpty()){
        EmptyScreen()
    }


        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding)
        ) {
            items(
                count = cats.size,
            ) {
                cats[it].let { cat ->
                    CatCard(cat = cat, onClick = { onClick(cat) })
                }
            }
        }


}

@Composable
fun CatsList(
    modifier: Modifier = Modifier,
    cats: LazyPagingItems<CatBreedsResponseItem>,
    onClick: (CatBreedsResponseItem) -> Unit
) {

    val handlePagingResult = handlePagingResult(cats)


    if (handlePagingResult) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding)
        ) {
            items(
                count = cats.itemCount,
            ) {
                cats[it]?.let { cat ->
                    CatCard(cat = cat, onClick = { onClick(cat) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(cats: LazyPagingItems<CatBreedsResponseItem>): Boolean {
    val loadState = cats.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            CatCardShimmerEffectGrid(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}