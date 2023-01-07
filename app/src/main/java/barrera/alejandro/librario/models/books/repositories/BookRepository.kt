package barrera.alejandro.librario.models.books.repositories

import barrera.alejandro.librario.models.books.entities.Book

interface BookRepository {
    suspend fun insertBook(book: Book)
}