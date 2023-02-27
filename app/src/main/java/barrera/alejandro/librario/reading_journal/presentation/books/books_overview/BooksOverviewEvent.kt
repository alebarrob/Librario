package barrera.alejandro.librario.reading_journal.presentation.books.books_overview

sealed class BooksOverviewEvent {
    data class OnQueryChange(val query: String) : BooksOverviewEvent()
    object OnSearch : BooksOverviewEvent()
    data class OnSearchFocusChange(val isFocused: Boolean): BooksOverviewEvent()
    object LoadBooks: BooksOverviewEvent()
}