package barrera.alejandro.librario.reading_journal.domain.di

import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository
import barrera.alejandro.librario.reading_journal.domain.books.use_case.*
import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository
import barrera.alejandro.librario.reading_journal.domain.characters.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ReadingJournalDomainModule {
    @ViewModelScoped
    @Provides
    fun provideBooksUseCases(
        bookRepository: BookRepository
    ): BooksUseCases {
        return BooksUseCases(
            insertBook = InsertBook(bookRepository),
            getAllBooks = GetAllBooks(bookRepository),
            getBookNotes = GetBookNotes(bookRepository),
            updateBook = UpdateBook(bookRepository),
            updateBookNotes = UpdateBookNotes(bookRepository),
            deleteBook = DeleteBook(bookRepository),
        )
    }

    @ViewModelScoped
    @Provides
    fun provideCharactersUseCases(
        characterRepository: CharacterRepository
    ): CharactersUseCases {
        return CharactersUseCases(
            insertCharacter = InsertCharacter(characterRepository),
            getBookCharacters = GetBookCharacters(characterRepository),
            updateCharacter = UpdateCharacter(characterRepository),
            deleteCharacter = DeleteCharacter(characterRepository),
            getPortraitPainterId = GetPortraitPainterId()
        )
    }
}