package barrera.alejandro.librario.reading_journal.domain.books.use_case

import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository

class DeleteBook(private val bookRepository: BookRepository) {
    suspend operator fun invoke(bookId: Int) {
        return bookRepository.deleteBook(bookId)
    }
}