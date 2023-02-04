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
    private val _bookTitle = MutableStateFlow(savedStateHandle.get<String>("bookTitle")!!)
    val bookTitle: Flow<String> get() = _bookTitle

    private val _bookAuthor = MutableStateFlow(savedStateHandle.get<String>("bookAuthor")!!)
    val bookAuthor: Flow<String> get() = _bookAuthor

    private val _bookDescription = MutableStateFlow(savedStateHandle.get<String>("bookDescription")!!)
    val bookDescription: Flow<String> get() = _bookDescription

    fun onBookTitleChange(bookTitle: String) {
        _bookTitle.value = bookTitle
    }

    fun onBookAuthorChange(bookAuthor: String) {
        _bookAuthor.value = bookAuthor
    }

    fun onBookDescriptionChange(bookDescription: String) {
        _bookDescription.value = bookDescription
    }

    fun deleteBook(bookTitle: String, bookAuthor: String) {
        viewModelScope.launch {
            bookRepositoryImpl.deleteBook(bookTitle, bookAuthor)
        }
    }
}