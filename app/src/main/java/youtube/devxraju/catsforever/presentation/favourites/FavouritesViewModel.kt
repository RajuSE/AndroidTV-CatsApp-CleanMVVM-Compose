package youtube.devxraju.catsforever.presentation.favourites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import youtube.devxraju.catsforever.domain.usecases.cats.GetSavedCats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getSavedCatsUseCase: GetSavedCats
) : ViewModel() {

    private val _state = mutableStateOf(FavouritesState())
    val state: State<FavouritesState> = _state

    init {
        getCats()
    }

    private fun getCats() {
        getSavedCatsUseCase().onEach {
            _state.value = _state.value.copy(cats = it)
        }.launchIn(viewModelScope)
    }
}