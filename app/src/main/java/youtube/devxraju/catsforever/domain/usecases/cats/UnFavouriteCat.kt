package youtube.devxraju.catsforever.domain.usecases.cats

import youtube.devxraju.catsforever.data.local.CatsDao
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import javax.inject.Inject

class UnFavouriteCat @Inject constructor(
    private val catsDao: CatsDao
) {

    suspend operator fun invoke(cat: CatBreedsResponseItem){
        catsDao.delete(cat = cat)
    }

}