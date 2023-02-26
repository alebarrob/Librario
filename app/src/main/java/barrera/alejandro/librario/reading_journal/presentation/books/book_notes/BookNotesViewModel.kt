package barrera.alejandro.librario.reading_journal.presentation.books.book_notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.domain.use_case.CoreUseCases
import barrera.alejandro.librario.core.domain.use_case.ValidateInfoNotEmpty
import barrera.alejandro.librario.core.util.UiEvent
import barrera.alejandro.librario.core.util.UiText
import barrera.alejandro.librario.reading_journal.domain.books.use_case.BooksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookNotesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val coreUseCases: CoreUseCases,
    private val booksUseCases: BooksUseCases
) : ViewModel() {
    var state by mutableStateOf(
        BookNotesState(bookId = savedStateHandle.get<Int>("bookId")!!)
    )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: BookNotesEvent) {
        when (event) {
            is BookNotesEvent.LoadNotes -> {
                viewModelScope.launch {
                    booksUseCases.getBookNotes(state.bookId).collect { savedNotes ->
                        state = state.copy(notes = savedNotes)
                    }
                }
            }
            is BookNotesEvent.OnNotesChange -> {
                state = state.copy(notes = event.notes)
            }
            is BookNotesEvent.OnSaveNotesClick -> {
                val defaultNotes = "Aquí puedes escribir tus notas sobre este libro."
                val result = coreUseCases.validateInfoNotEmpty(listOf(state.notes))

                viewModelScope.launch {
                    booksUseCases.updateBookNotes(
                        bookNotes = when (result) {
                            is ValidateInfoNotEmpty.Result.Success -> state.notes
                            else -> defaultNotes
                        },
                        bookId = state.bookId
                    )
                    _uiEvent.send(UiEvent.NavigateUp)
                    _uiEvent.send(
                        UiEvent.ShowToast(
                            UiText.StringResource(R.string.update_notes_success)
                        )
                    )
                }
            }
        }
    }
}