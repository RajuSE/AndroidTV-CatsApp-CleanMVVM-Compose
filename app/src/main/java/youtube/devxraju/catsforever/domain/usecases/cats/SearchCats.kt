package youtube.devxraju.catsforever.domain.usecases.cats

import androidx.paging.PagingData
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import youtube.devxraju.catsforever.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCats @Inject constructor(
    private val catsRepository: CatsRepository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<CatBreedsResponseItem>> {
        return catsRepository.searchCats(
            searchQuery = searchQuery,
        )
    }
}