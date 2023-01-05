package barrera.alejandro.librario.models.repositories

import barrera.alejandro.librario.models.entities.Book

interface BookRepository {
    suspend fun insertBook(book: Book)
}