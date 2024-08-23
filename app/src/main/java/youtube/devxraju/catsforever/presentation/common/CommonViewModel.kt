package youtube.devxraju.catsforever.presentation.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import youtube.devxraju.catsforever.domain.usecases.cats.GetCatDetailsFromDB
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
    private val getCatDetailsFromDBUseCase: GetCatDetailsFromDB,
) : ViewModel() {

    var currentCat by mutableStateOf<CatBreedsResponseItem?>(null)
        private set

    var isCurrentCatFav by mutableStateOf<Boolean>(false)
        private set

    var webviewURL by mutableStateOf<String?>(null)
        private set


    init {
        println("VMVM: init isFav:${isCurrentCatFav} id:${currentCat?.id} this:$this")
    }

    suspend fun onCatClicked(catBreedsResponseItem: CatBreedsResponseItem) {
        currentCat = catBreedsResponseItem
        CoroutineScope(Dispatchers.IO).async {
            isCurrentCatFav = getCatDetailsFromDBUseCase.invoke(currentCat!!.id) != null
        }.await()
        println("VMVM: ${currentCat?.id}");
    }

    fun onWebViewClicked(webviewURL:String){
        this.webviewURL=webviewURL
    }

}