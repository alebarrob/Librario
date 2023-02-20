package barrera.alejandro.librario.books.data.book.repository

import barrera.alejandro.librario.books.data.book.entity.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    val books: Flow<List<Book>>

    fun getBookColor(bookId: Int): Flow<String>
    fun getBookNotes(bookId: Int): Flow<String>
    suspend fun insertBook(book: Book)
    suspend fun updateBook(bookTitle: String, bookAuthor: String, bookDescription: String, bookId: Int)
    suspend fun changeColor(bookColor: String, bookId: Int)
    suspend fun updateNotes(bookNotes: String, bookId: Int)
    suspend fun deleteBook(bookId: Int)
}