package barrera.alejandro.librario.models.books.repositories

import barrera.alejandro.librario.models.books.entities.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    val books: Flow<List<Book>>

    suspend fun insertBook(book: Book)
}