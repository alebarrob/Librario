package barrera.alejandro.librario.reading_journal.domain.books.use_case

import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository

class UpdateBook(private val bookRepository: BookRepository) {
    suspend operator fun invoke(
        title: String,
        author: String,
        description: String,
        bookId: Int
    ) {
        return bookRepository.updateBook(title, author, description, bookId)
    }
}