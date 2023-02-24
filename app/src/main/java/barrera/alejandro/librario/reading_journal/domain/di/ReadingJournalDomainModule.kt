package barrera.alejandro.librario.reading_journal.domain.di

import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository
import barrera.alejandro.librario.reading_journal.domain.books.use_case.*
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
    fun provideBooksUseCases(bookRepository: BookRepository): BooksUseCases {
        return BooksUseCases(
            getAllBooks = GetAllBooks(bookRepository),
            updateBook = UpdateBook(bookRepository),
            insertBook = InsertBook(bookRepository),
            deleteBook = DeleteBook(bookRepository),
            getBookNotes = GetBookNotes(bookRepository),
            updateBookNotes = UpdateBookNotes(bookRepository),
            validateBookInfo = ValidateBookInfo(),
            validateBookNotes = ValidateBookNotes()
        )
    }
}