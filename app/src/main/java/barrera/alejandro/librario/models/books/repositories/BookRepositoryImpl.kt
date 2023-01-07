package barrera.alejandro.librario.models.books.repositories

import barrera.alejandro.librario.models.books.daos.BookDao
import barrera.alejandro.librario.models.books.entities.Book
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : BookRepository {
    override suspend fun insertBook(book: Book) = bookDao.insertBook(book)
}