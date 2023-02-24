package barrera.alejandro.librario.reading_journal.data.di

import barrera.alejandro.librario.core.data.database.LibrarioRoomDatabase
import barrera.alejandro.librario.reading_journal.data.books.dao.BookDao
import barrera.alejandro.librario.reading_journal.data.characters.dao.CharacterDao
import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository
import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepositoryImpl
import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository
import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReadingJournalDataModule {
    @Singleton
    @Provides
    fun provideBookDao(dataBase: LibrarioRoomDatabase): BookDao {
        return dataBase.bookDao()
    }

    @Singleton
    @Provides
    fun provideBookRepository(bookDao: BookDao): BookRepository {
        return BookRepositoryImpl(bookDao)
    }

    @Singleton
    @Provides
    fun provideCharacterDao(dataBase: LibrarioRoomDatabase): CharacterDao {
        return dataBase.characterDao()
    }

    @Singleton
    @Provides
    fun provideCharacterRepository(characterDao: CharacterDao): CharacterRepository {
        return CharacterRepositoryImpl(characterDao)
    }
}