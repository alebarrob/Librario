package barrera.alejandro.librario.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.librario.models.books.daos.BookDao
import barrera.alejandro.librario.models.books.entities.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class LibrarioRoomDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao
}