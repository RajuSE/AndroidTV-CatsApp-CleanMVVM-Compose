package youtube.devxraju.catsforever.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem

class CatsPagingSource(
    private val catsApi: CatsApi,
) : PagingSource<Int, CatBreedsResponseItem>() {


    override fun getRefreshKey(state: PagingState<Int, CatBreedsResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalDataCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatBreedsResponseItem> {
        val page = 2
        return try {
            val catsResponse = catsApi.getCatsApi(limit = 10, page = page)
            totalDataCount += catsResponse.size
            val cats = catsResponse.distinctBy { it.id } //Remove duplicates

            LoadResult.Page(
                data = cats,
                nextKey = if (totalDataCount == 10) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}