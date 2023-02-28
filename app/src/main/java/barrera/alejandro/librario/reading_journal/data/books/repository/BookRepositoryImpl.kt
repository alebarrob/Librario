package barrera.alejandro.librario.reading_journal.data.books.repository

import barrera.alejandro.librario.reading_journal.data.books.dao.BookDao
import barrera.alejandro.librario.reading_journal.data.books.mapper.toBook
import barrera.alejandro.librario.reading_journal.domain.books.model.Book
import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : BookRepository {
    override suspend fun insertBook(title: String, author: String, description: String) {
        bookDao.insertBook(title, author, description)
    }

    override fun getAllBooks(): Flow<List<Book>> {
        return bookDao.getAllBooks().map { entities ->
                entities.map { it.toBook() }
        }
    }

    override fun getBookNotes(bookId: Int): Flow<String> {
        return bookDao.getBookNotes(bookId)
    }

    override suspend fun updateBook(
        title: String,
        author: String,
        description: String,
        bookId: Int
    ) {
        bookDao.updateBook(title, author, description, bookId)
    }

    override suspend fun updateBookNotes(bookNotes: String, bookId: Int) {
        bookDao.updateBookNotes(bookNotes, bookId)
    }

    override suspend fun deleteBook(bookId: Int) = bookDao.deleteBook(bookId)
}