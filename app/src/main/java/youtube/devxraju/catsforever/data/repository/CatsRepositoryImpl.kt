package youtube.devxraju.catsforever.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import youtube.devxraju.catsforever.data.local.CatsDao
import youtube.devxraju.catsforever.data.remote.CatsPagingSource
import youtube.devxraju.catsforever.data.remote.CatsApi
import youtube.devxraju.catsforever.data.remote.SearchCatsPagingSource
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import youtube.devxraju.catsforever.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val catsApi: CatsApi,
    private val catsDao: CatsDao
) : CatsRepository {

    override fun getCats(): Flow<PagingData<CatBreedsResponseItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CatsPagingSource(catsApi = catsApi)
            }
        ).flow
    }

    override fun searchCats(searchQuery: String): Flow<PagingData<CatBreedsResponseItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchCatsPagingSource(
                    api = catsApi,
                    searchQuery = searchQuery,
                )
            }
        ).flow
    }

  
}