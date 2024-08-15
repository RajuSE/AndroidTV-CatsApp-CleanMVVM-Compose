package youtube.devxraju.catsforever.presentation.search

import androidx.paging.PagingData
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val cats: Flow<PagingData<CatBreedsResponseItem>>? = null
)