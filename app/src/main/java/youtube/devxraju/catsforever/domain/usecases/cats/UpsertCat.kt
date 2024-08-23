package youtube.devxraju.catsforever.domain.usecases.cats

import youtube.devxraju.catsforever.data.local.CatsDao
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import javax.inject.Inject

class UpsertCat @Inject constructor(
    private val catsDao: CatsDao
) {

     operator fun invoke(cat: CatBreedsResponseItem){
        catsDao.upsert(cat = cat)
    }

}