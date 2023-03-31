package barrera.alejandro.librario.reading_journal.domain.books.use_case

import barrera.alejandro.librario.core.domain.model.Book
import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllBooks(private val bookRepository: BookRepository) {
    operator fun invoke(query: String): Flow<List<Book>> {
        return if (query.isBlank()) {
            bookRepository.getAllBooks()
        } else {
            bookRepository.getAllBooks().map { books ->
                books.filter { book ->
                    book.title.trim().contains(query, ignoreCase = true)
                }
            }
        }
    }
}