package barrera.alejandro.librario.models.books.repositories

import barrera.alejandro.librario.models.books.entities.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    val books: Flow<List<Book>>

    fun getBook(bookTitle: String, bookAuthor: String): Flow<Book>
    suspend fun insertBook(book: Book)
    suspend fun deleteBook(book: Book)
}