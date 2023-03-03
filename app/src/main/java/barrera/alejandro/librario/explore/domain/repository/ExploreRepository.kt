package barrera.alejandro.librario.explore.domain.repository

import barrera.alejandro.librario.core.domain.model.Book

interface ExploreRepository {
    suspend fun searchBooks(
        query: String,
        key: String
    ): List<Book>
}