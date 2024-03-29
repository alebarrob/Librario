package barrera.alejandro.librario.explore.domain.use_case

import barrera.alejandro.librario.core.domain.model.Book
import barrera.alejandro.librario.explore.data.constants.Constants
import barrera.alejandro.librario.explore.domain.repository.ExploreRepository

class SearchBook(
    private val repository: ExploreRepository
) {
    suspend operator fun invoke(
        query: String,
        // Add your own Google Books API key
        key: String = Constants.API_KEY
    ): Result<List<Book>> {
         return if (query.isBlank()) {
            Result.success(emptyList())
        } else {
             repository.searchBooks(query.trim(), key)
         }
    }
}