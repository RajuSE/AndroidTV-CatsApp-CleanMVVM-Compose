package youtube.devxraju.catsforever.domain.usecases.cats

import androidx.paging.PagingData
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import youtube.devxraju.catsforever.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatsData @Inject constructor(
    private val catsRepository: CatsRepository
) {
    operator fun invoke(): Flow<PagingData<CatBreedsResponseItem>> {
        return catsRepository.getCats()
    }
}