package barrera.alejandro.librario.models.books.repositories

import barrera.alejandro.librario.models.books.daos.BookDao
import barrera.alejandro.librario.models.books.entities.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : BookRepository {
    override val books: Flow<List<Book>> get() = bookDao.getAllBooks()

    override suspend fun insertBook(book: Book) = bookDao.insertBook(book)
}