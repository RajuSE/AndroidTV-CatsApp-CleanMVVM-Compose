package youtube.devxraju.catsforever.domain.usecases.cats

import youtube.devxraju.catsforever.data.local.CatsDao
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class  GetSavedCats @Inject constructor(
    private val catsDao: CatsDao
) {

    operator fun invoke(): Flow<List<CatBreedsResponseItem>>{
        return catsDao.getCats()
    }

}