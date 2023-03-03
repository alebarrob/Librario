package barrera.alejandro.librario.reading_journal.presentation.books.books_overview

import barrera.alejandro.librario.core.domain.model.Book

data class BooksOverviewState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val books: List<Book> = emptyList()
)
