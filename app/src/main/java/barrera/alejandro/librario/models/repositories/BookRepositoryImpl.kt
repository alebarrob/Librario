package barrera.alejandro.librario.models.repositories

import barrera.alejandro.librario.models.daos.BookDao
import barrera.alejandro.librario.models.entities.Book
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : BookRepository {
    override suspend fun insertBook(book: Book) = bookDao.insertBook(book)
}