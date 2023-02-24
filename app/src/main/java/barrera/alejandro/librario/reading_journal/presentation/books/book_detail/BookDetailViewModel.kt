package barrera.alejandro.librario.reading_journal.presentation.books.book_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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
class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: BooksUseCases
) : ViewModel() {
    var state by mutableStateOf(
        BookDetailState(
            id = savedStateHandle.get<Int>("bookId")!!,
            title = savedStateHandle.get<String>("title")!!,
            author = savedStateHandle.get<String>("author")!!,
            description = savedStateHandle.get<String>("description")!!
        )
    )
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: BookDetailEvent) {
        when (event) {
            is BookDetailEvent.OnTitleChange -> {
                state = state.copy(title = event.title)
            }
            is BookDetailEvent.OnAuthorChange -> {
                state = state.copy(author = event.author)
            }
            is BookDetailEvent.OnDescriptionChange -> {
                state = state.copy(description = event.description)
            }
            is BookDetailEvent.OnSaveChangesClick -> {
                val result = useCases.validateBookInfo(
                    state.title,
                    state.author,
                    state.description
                )

                when (result) {
                    is ValidateBookInfo.Result.Success -> {
                        viewModelScope.launch {
                            useCases.updateBook(
                                title = state.title,
                                author = state.author,
                                description = state.description,
                                bookId = state.id
                            )
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.update_book_success)
                                )
                            )
                        }
                    }
                    is ValidateBookInfo.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.update_book_error)
                                )
                            )
                        }
                    }
                }
            }
            is BookDetailEvent.OnDeleteBookConfirmation -> {
                viewModelScope.launch {
                    useCases.deleteBook(state.id)
                    _uiEvent.send(UiEvent.NavigateUp)
                    _uiEvent.send(
                        UiEvent.ShowToast(
                            UiText.StringResource(R.string.delete_book_confirmation)
                        )
                    )
                }
            }
        }
    }
}