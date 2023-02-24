package barrera.alejandro.librario.reading_journal.presentation.books.books_overview

import androidx.lifecycle.ViewModel
import barrera.alejandro.librario.reading_journal.data.books.entity.Book
import barrera.alejandro.librario.reading_journal.domain.books.use_case.BooksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BooksOverviewViewModel @Inject constructor(
    booksUseCases: BooksUseCases
) : ViewModel() {
    val books: Flow<List<Book>> = booksUseCases.getAllBooks()
}