package barrera.alejandro.librario.core.data.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.librario.books.data.book.dao.BookDao
import barrera.alejandro.librario.books.data.character.dao.CharacterDao
import barrera.alejandro.librario.books.data.book.repository.BookRepository
import barrera.alejandro.librario.books.data.book.repository.BookRepositoryImpl
import barrera.alejandro.librario.books.data.character.repository.CharacterRepository
import barrera.alejandro.librario.books.data.character.repository.CharacterRepositoryImpl
import barrera.alejandro.librario.core.data.database.LibrarioRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        LibrarioRoomDatabase::class.java,
        "librario_database"
    ).fallbackToDestructiveMigration().createFromAsset("database/librario_database.db")
        .build()

    @Singleton
    @Provides
    fun provideBookDao(dataBase: LibrarioRoomDatabase) = dataBase.bookDao()

    @Singleton
    @Provides
    fun provideCharacterDao(dataBase: LibrarioRoomDatabase) = dataBase.characterDao()

    @Singleton
    @Provides
    fun booksRepositoryImpl(bookDao: BookDao): BookRepository = BookRepositoryImpl(bookDao)

    @Singleton
    @Provides
    fun characterRepositoryImpl(characterDao: CharacterDao): CharacterRepository = CharacterRepositoryImpl(characterDao)
}