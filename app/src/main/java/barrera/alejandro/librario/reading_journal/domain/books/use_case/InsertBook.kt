package barrera.alejandro.librario.reading_journal.domain.books.use_case

import barrera.alejandro.librario.reading_journal.domain.books.repository.BookRepository

class InsertBook(private val bookRepository: BookRepository) {
    suspend operator fun invoke(title: String, author: String, description: String) {
        bookRepository.insertBook(title, author, description)
    }
}