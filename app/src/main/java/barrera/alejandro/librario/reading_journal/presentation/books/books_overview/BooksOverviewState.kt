package barrera.alejandro.librario.reading_journal.presentation.books.books_overview

import barrera.alejandro.librario.reading_journal.domain.books.model.Book

data class BooksOverviewState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val bookEntities: List<Book> = emptyList()
)
