package barrera.alejandro.librario.viewmodels.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.models.books.entities.Book
import barrera.alejandro.librario.models.books.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertBookScreenViewModel @Inject constructor(
    private val bookRepositoryImpl: BookRepository
) : ViewModel() {
    fun insertBook(book: Book) {
        viewModelScope.launch {
            bookRepositoryImpl.insertBook(book)
        }
    }
}