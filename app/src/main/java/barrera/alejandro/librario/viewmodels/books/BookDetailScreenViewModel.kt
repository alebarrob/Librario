package barrera.alejandro.librario.viewmodels.books

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.models.books.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepositoryImpl: BookRepository
) : ViewModel() {
    private val _bookId = MutableStateFlow(savedStateHandle.get<Int>("bookId")!!)
    val bookId: Flow<Int> get() = _bookId

    private val _bookTitle = MutableStateFlow(savedStateHandle.get<String>("bookTitle")!!)
    val bookTitle: Flow<String> get() = _bookTitle

    private val _bookAuthor = MutableStateFlow(savedStateHandle.get<String>("bookAuthor")!!)
    val bookAuthor: Flow<String> get() = _bookAuthor

    private val _bookDescription = MutableStateFlow(savedStateHandle.get<String>("bookDescription")!!)
    val bookDescription: Flow<String> get() = _bookDescription

    val bookColor: Flow<String> = bookRepositoryImpl.getBookColor(_bookId.value)

    fun onBookTitleChange(bookTitle: String) {
        _bookTitle.value = bookTitle
    }

    fun onBookAuthorChange(bookAuthor: String) {
        _bookAuthor.value = bookAuthor
    }

    fun onBookDescriptionChange(bookDescription: String) {
        _bookDescription.value = bookDescription
    }

    fun updateBook(bookTitle: String, bookAuthor: String, bookDescription: String, bookId: Int) {
        viewModelScope.launch {
            bookRepositoryImpl.updateBook(bookTitle, bookAuthor, bookDescription, bookId)
        }
    }

    fun deleteBook(bookId: Int) {
        viewModelScope.launch {
            bookRepositoryImpl.deleteBook(bookId)
        }
    }
}