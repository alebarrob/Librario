package barrera.alejandro.librario.reading_journal.presentation.books.books_overview

sealed class BooksOverviewEvent {
    data class OnQueryChange(val query: String) : BooksOverviewEvent()
    object OnSearch : BooksOverviewEvent()
    object LoadBooks: BooksOverviewEvent()
}