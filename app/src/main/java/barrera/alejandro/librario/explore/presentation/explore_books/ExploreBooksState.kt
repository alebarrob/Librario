package barrera.alejandro.librario.explore.presentation.explore_books

import barrera.alejandro.librario.reading_journal.data.books.entity.BookEntity

data class ExploreBooksState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val bookEntities: List<BookEntity> = emptyList()
)
