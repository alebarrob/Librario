package barrera.alejandro.librario.models.books.repositories

import barrera.alejandro.librario.models.books.daos.BookDao
import barrera.alejandro.librario.models.books.entities.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : BookRepository {
    override val books: Flow<List<Book>> get() = bookDao.getAllBooks()

    override fun getBookColor(bookId: Int): Flow<String>  = bookDao.getBookColor(bookId)
    override fun getBookNotes(bookId: Int): Flow<String> = bookDao.getBookNotes(bookId)
    override suspend fun insertBook(book: Book) = bookDao.insertBook(book)
    override suspend fun updateBook(bookTitle: String, bookAuthor: String, bookDescription: String, bookId: Int) {
        bookDao.updateBook(bookTitle, bookAuthor, bookDescription, bookId)
    }
    override suspend fun changeColor(bookColor: String, bookId: Int) = bookDao.changeColor(bookColor, bookId)
    override suspend fun updateNotes(bookNotes: String, bookId: Int) = bookDao.updateNotes(bookNotes, bookId)
    override suspend fun deleteBook(bookId: Int) = bookDao.deleteBook(bookId)
}