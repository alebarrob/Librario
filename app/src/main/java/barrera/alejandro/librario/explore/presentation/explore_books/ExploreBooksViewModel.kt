package barrera.alejandro.librario.explore.presentation.explore_books

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.explore.domain.use_case.ExploreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreBooksViewModel @Inject constructor(
    private val exploreUseCases: ExploreUseCases
) : ViewModel() {
    var state by mutableStateOf(ExploreBooksState())
        private set

    fun onEvent(event: ExploreBooksEvent) {
        when (event) {
            is ExploreBooksEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            is ExploreBooksEvent.OnSearch -> {
                viewModelScope.launch {
                    state = state.copy(
                        books = exploreUseCases.searchBook(query = state.query)
                    )
                }
            }
            is ExploreBooksEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
        }
    }
}