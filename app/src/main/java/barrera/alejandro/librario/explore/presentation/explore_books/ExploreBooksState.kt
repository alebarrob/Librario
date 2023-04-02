package barrera.alejandro.librario.explore.presentation.explore_books

import barrera.alejandro.librario.core.domain.model.Book

data class ExploreBooksState(
    val query: String = "",
    val books: List<Book> = emptyList()
)
