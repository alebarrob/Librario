package barrera.alejandro.librario.reading_journal.domain.books.repository

import barrera.alejandro.librario.reading_journal.data.books.entity.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun insertBook(title: String, author: String, description: String)

    fun getAllBooks(): Flow<List<Book>>

    fun getBookNotes(bookId: Int): Flow<String>

    suspend fun updateBook(title: String, author: String, description: String, bookId: Int)

    suspend fun updateBookNotes(bookNotes: String, bookId: Int)

    suspend fun deleteBook(bookId: Int)
}