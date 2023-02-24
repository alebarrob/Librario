package barrera.alejandro.librario.reading_journal.presentation.books.add_book

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.util.UiEvent
import barrera.alejandro.librario.core.util.UiText
import barrera.alejandro.librario.reading_journal.domain.books.use_case.BooksUseCases
import barrera.alejandro.librario.reading_journal.domain.books.use_case.ValidateBookInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val useCases: BooksUseCases
) : ViewModel() {
    var state by mutableStateOf(AddBookState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddBookEvent) {
        when (event) {
            is AddBookEvent.OnTitleChange -> {
                state = state.copy(
                    title = event.title
                )
            }
            is AddBookEvent.OnAuthorChange -> {
                state = state.copy(
                    author = event.author
                )
            }
            is AddBookEvent.OnDescriptionChange -> {
                state = state.copy(
                    description = event.description
                )
            }
            is AddBookEvent.OnAddBookClick -> {
                val result = useCases.validateBookInfo(
                    state.title,
                    state.author,
                    state.description
                )

                when (result) {
                    is ValidateBookInfo.Result.Success -> {
                        viewModelScope.launch {
                            useCases.insertBook(
                                state.title,
                                state.author,
                                state.description
                            )
                            _uiEvent.send(UiEvent.NavigateUp)
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.add_book_success)
                                )
                            )
                        }
                    }
                    is ValidateBookInfo.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.add_book_error)
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}