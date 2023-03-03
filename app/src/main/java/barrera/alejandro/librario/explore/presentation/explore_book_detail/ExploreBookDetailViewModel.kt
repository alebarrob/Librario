package barrera.alejandro.librario.explore.presentation.explore_book_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ExploreBookDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(
        ExploreBookDetailState(
            title = savedStateHandle.get<String>("title")!!,
            author = savedStateHandle.get<String>("author")!!,
            description = savedStateHandle.get<String>("description")!!
        )
    )
        private set
}