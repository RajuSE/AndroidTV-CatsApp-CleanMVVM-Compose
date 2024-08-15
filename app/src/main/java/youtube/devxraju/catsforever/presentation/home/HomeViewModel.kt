package youtube.devxraju.catsforever.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import youtube.devxraju.catsforever.domain.usecases.cats.GetCatsData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCatsDataUseCase: GetCatsData
): ViewModel() {

    var state = mutableStateOf(HomeState())
        private set

    val catsData = getCatsDataUseCase().cachedIn(viewModelScope)

}