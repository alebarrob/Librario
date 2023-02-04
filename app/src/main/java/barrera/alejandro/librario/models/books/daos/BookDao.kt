package barrera.alejandro.librario.models.books.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import barrera.alejandro.librario.models.books.entities.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE title = :bookTitle AND author = :bookAuthor")
    fun getBook(bookTitle: String, bookAuthor: String): Flow<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Query("DELETE FROM books WHERE title = :bookTitle AND author = :bookAuthor")
    suspend fun deleteBook(bookTitle: String, bookAuthor: String)
}