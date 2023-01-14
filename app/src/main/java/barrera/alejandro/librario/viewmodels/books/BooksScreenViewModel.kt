package barrera.alejandro.librario.viewmodels.books

import androidx.lifecycle.ViewModel
import barrera.alejandro.librario.models.books.entities.Book
import barrera.alejandro.librario.models.books.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BooksScreenViewModel @Inject constructor(
    bookRepositoryImpl: BookRepository
) : ViewModel() {
    val books: Flow<List<Book>> = bookRepositoryImpl.books
}