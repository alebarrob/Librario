package barrera.alejandro.librario.books.presentation.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.books.data.book.entity.Book
import barrera.alejandro.librario.books.data.book.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookScreenViewModel @Inject constructor(
    private val bookRepositoryImpl: BookRepository
) : ViewModel() {

    fun insertBook(book: Book) {
        viewModelScope.launch {
            bookRepositoryImpl.insertBook(book)
        }
    }
}