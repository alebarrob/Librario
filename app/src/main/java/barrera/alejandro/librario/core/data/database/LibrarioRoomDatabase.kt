package barrera.alejandro.librario.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.librario.books.data.book.dao.BookDao
import barrera.alejandro.librario.books.data.character.dao.CharacterDao
import barrera.alejandro.librario.books.data.book.entity.Book
import barrera.alejandro.librario.books.data.character.entity.Character

@Database(entities = [Book::class, Character::class], version = 1, exportSchema = false)
abstract class LibrarioRoomDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao
    abstract fun characterDao() : CharacterDao
}