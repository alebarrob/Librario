package barrera.alejandro.librario.models.books.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import barrera.alejandro.librario.models.books.entities.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBook(book: Book)
}