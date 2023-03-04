package barrera.alejandro.librario.reading_journal.presentation.books.book_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.domain.use_case.CoreUseCases
import barrera.alejandro.librario.core.util.UiEvent
import barrera.alejandro.librario.core.util.UiText
import barrera.alejandro.librario.reading_journal.domain.books.use_case.BooksUseCases
import barrera.alejandro.librario.core.domain.use_case.ValidateInfoNotEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val coreUseCases: CoreUseCases,
    private val booksUseCases: BooksUseCases
) : ViewModel() {
    var state by mutableStateOf(
        BookDetailState(
            bookId = savedStateHandle.get<Int>("bookId")!!,
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
                val result = coreUseCases.validateInfoNotEmpty(
                    listOf(state.title, state.author, state.description)
                )

                when (result) {
                    is ValidateInfoNotEmpty.Result.Success -> {
                        viewModelScope.launch {
                            booksUseCases.updateBook(
                                title = coreUseCases.slashToDashConverter(state.title),
                                author = coreUseCases.slashToDashConverter(state.author),
                                description = coreUseCases.slashToDashConverter(state.description),
                                bookId = state.bookId
                            )
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.update_book_success)
                                )
                            )
                        }
                    }
                    is ValidateInfoNotEmpty.Result.Error -> {
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
                    booksUseCases.deleteBook(state.bookId)
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