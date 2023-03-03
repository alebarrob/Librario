package barrera.alejandro.librario.explore.data.repository

import barrera.alejandro.librario.core.data.mapper.toBook
import barrera.alejandro.librario.core.domain.model.Book
import barrera.alejandro.librario.explore.data.api.GoogleBooksApi
import barrera.alejandro.librario.explore.domain.repository.ExploreRepository

class ExploreRepositoryImpl(
    private val api: GoogleBooksApi
): ExploreRepository {
    override suspend fun searchBooks(
        query: String,
        key: String
    ): List<Book> {
        val search = api.getBooks(query, key).body() ?: return emptyList()

        return search.items.map { item -> item.volumeInfo.toBook() }
    }
}