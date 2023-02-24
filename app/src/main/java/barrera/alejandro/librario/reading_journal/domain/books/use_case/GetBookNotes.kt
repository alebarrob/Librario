package barrera.alejandro.librario.reading_journal.domain.books.use_case

import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetBookNotes(private val bookRepository: BookRepository) {
    operator fun invoke(bookId: Int): Flow<String> {
        return bookRepository.getBookNotes(bookId)
    }
}