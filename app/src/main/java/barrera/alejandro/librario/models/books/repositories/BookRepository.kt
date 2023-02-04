package barrera.alejandro.librario.models.books.repositories

import barrera.alejandro.librario.models.books.entities.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    val books: Flow<List<Book>>

    fun getBookColor(bookId: Int): Flow<String>
    suspend fun insertBook(book: Book)
    suspend fun updateBook(bookTitle: String, bookAuthor: String, bookDescription: String, bookId: Int)
    suspend fun changeColor(bookColor: String, bookId: Int)
    suspend fun deleteBook(bookId: Int)
}