package barrera.alejandro.librario.explore.domain.use_case

import barrera.alejandro.librario.core.domain.model.Book
import barrera.alejandro.librario.explore.data.constants.Constants
import barrera.alejandro.librario.explore.domain.repository.ExploreRepository

// Here you can add your own API_KEY constant in order to use the Google Books API

class SearchBook(
    private val repository: ExploreRepository
) {
    suspend operator fun invoke(
        query: String,
        key: String = Constants.API_KEY
    ): List<Book> {
        return repository.searchBooks(query, key)
    }
}