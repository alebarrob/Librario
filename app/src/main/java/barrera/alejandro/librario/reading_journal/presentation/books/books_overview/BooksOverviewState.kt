package barrera.alejandro.librario.reading_journal.presentation.books.books_overview

import barrera.alejandro.librario.reading_journal.data.books.entity.Book

data class BooksOverviewState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val books: List<Book> = emptyList()
)