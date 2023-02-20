package barrera.alejandro.librario.books.presentation.book

import androidx.lifecycle.ViewModel
import barrera.alejandro.librario.books.data.book.entity.Book
import barrera.alejandro.librario.books.data.book.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BooksScreenViewModel @Inject constructor(
    bookRepositoryImpl: BookRepository
) : ViewModel() {
    val books: Flow<List<Book>> = bookRepositoryImpl.books
}