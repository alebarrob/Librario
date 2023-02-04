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

    @Query("SELECT color FROM books WHERE id = :bookId")
    fun getBookColor(bookId: Int): Flow<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Query("UPDATE books SET title = :bookTitle, author = :bookAuthor, description = :bookDescription WHERE id = :bookId")
    suspend fun updateBook(bookTitle: String, bookAuthor: String, bookDescription: String, bookId: Int)

    @Query("UPDATE books SET color = :bookColor WHERE id = :bookId")
    suspend fun changeColor(bookColor: String, bookId: Int)

    @Query("DELETE FROM books WHERE id = :bookId")
    suspend fun deleteBook(bookId: Int)
}