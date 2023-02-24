package barrera.alejandro.librario.reading_journal.presentation.books.book_notes

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
import barrera.alejandro.librario.reading_journal.domain.books.use_case.ValidateBookNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookNotesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: BooksUseCases
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
                    useCases.getBookNotes(state.bookId).collect { savedNotes ->
                        state = state.copy(notes = savedNotes)
                    }
                }
            }
            is BookNotesEvent.OnNotesChange -> {
                state = state.copy(notes = event.notes)
            }
            is BookNotesEvent.OnSaveNotesClick -> {
                val defaultNotes = "Aquí puedes escribir tus notas sobre este libro."
                val result = useCases.validateBookNotes(state.notes)

                viewModelScope.launch {
                    useCases.updateBookNotes(
                        bookNotes = when (result) {
                            is ValidateBookNotes.Result.Success -> state.notes
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