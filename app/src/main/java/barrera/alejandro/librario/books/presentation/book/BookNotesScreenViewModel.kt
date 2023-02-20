package barrera.alejandro.librario.books.presentation.book

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.books.data.book.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookNotesScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepositoryImpl: BookRepository
) : ViewModel() {
    private val _bookId = MutableStateFlow(savedStateHandle.get<Int>("bookId")!!)
    val bookId: Flow<Int> get() = _bookId

    private val _bookNotes = MutableStateFlow(savedStateHandle.get<String>("bookNotes")!!)
    val bookNotes: Flow<String> get() = _bookNotes

    fun onBookNotesChange(bookNotes: String) {
        _bookNotes.value = bookNotes
    }

    fun updateNotes(bookNotes: String, bookId: Int) {
        viewModelScope.launch {
            bookRepositoryImpl.updateNotes(bookNotes, bookId)
        }
    }
}