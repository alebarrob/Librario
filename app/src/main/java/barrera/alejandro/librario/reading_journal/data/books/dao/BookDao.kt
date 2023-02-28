package barrera.alejandro.librario.reading_journal.data.books.dao

import androidx.room.Dao
import androidx.room.Query
import barrera.alejandro.librario.reading_journal.data.books.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("INSERT INTO books (title, author, description) VALUES (:title, :author, :description)")
    suspend fun insertBook(title: String, author: String, description: String)

    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT notes FROM books WHERE id = :bookId")
    fun getBookNotes(bookId: Int): Flow<String>

    @Query("""
    UPDATE books 
    SET title = :title, author = :author, description = :description 
    WHERE id = :bookId
    """)
    suspend fun updateBook(title: String, author: String, description: String, bookId: Int)

    @Query("UPDATE books SET notes = :bookNotes WHERE id = :bookId")
    suspend fun updateBookNotes(bookNotes: String, bookId: Int)

    @Query("DELETE FROM books WHERE id = :bookId")
    suspend fun deleteBook(bookId: Int)
}