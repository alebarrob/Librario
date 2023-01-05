package barrera.alejandro.librario.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.librario.models.daos.BookDao
import barrera.alejandro.librario.models.database.LibrarioRoomDatabase
import barrera.alejandro.librario.models.repositories.BookRepository
import barrera.alejandro.librario.models.repositories.BookRepositoryImpl
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
    ).fallbackToDestructiveMigration()
        .createFromAsset("database/librario_database.db")
        .build()

    @Singleton
    @Provides
    fun provideBookDao(dataBase: LibrarioRoomDatabase) = dataBase.bookDao()

    @Singleton
    @Provides
    fun booksRepositoryImpl(bookDao: BookDao): BookRepository = BookRepositoryImpl(bookDao)
}