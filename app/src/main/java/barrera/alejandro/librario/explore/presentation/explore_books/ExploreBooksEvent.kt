package barrera.alejandro.librario.explore.presentation.explore_books

sealed class ExploreBooksEvent {
    data class OnQueryChange(val query: String) : ExploreBooksEvent()
    object OnSearch : ExploreBooksEvent()
}