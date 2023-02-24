package barrera.alejandro.librario.reading_journal.domain.books.use_case

import barrera.alejandro.librario.reading_journal.data.books.entity.Book
import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetAllBooks(private val bookRepository: BookRepository) {
    operator fun invoke(): Flow<List<Book>> {
        return bookRepository.getAllBooks()
    }
}