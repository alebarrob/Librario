package barrera.alejandro.librario.reading_journal.domain.books.use_case

import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository

class UpdateBookNotes(private val bookRepository: BookRepository) {
    suspend operator fun invoke(bookNotes: String, bookId: Int) {
        bookRepository.updateBookNotes(bookNotes, bookId)
    }
}