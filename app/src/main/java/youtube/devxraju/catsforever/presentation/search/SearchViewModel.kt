package youtube.devxraju.catsforever.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import youtube.devxraju.catsforever.domain.usecases.cats.SearchCats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCatsUseCase: SearchCats
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchCats -> {
                searchCats()
            }
        }
    }

    private fun searchCats() {
        println("searchCats called")
        val cats = searchCatsUseCase(
            searchQuery = _state.value.searchQuery,
        ).distinctUntilChanged().cachedIn(viewModelScope)
        println("searchCats copy value called ")
        _state.value = _state.value.copy(cats = cats)
    }


}