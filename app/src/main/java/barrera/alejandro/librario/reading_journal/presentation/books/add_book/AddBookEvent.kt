package barrera.alejandro.librario.reading_journal.presentation.books.add_book

sealed class AddBookEvent {
    data class OnTitleChange(val title: String): AddBookEvent()
    data class OnAuthorChange(val author: String): AddBookEvent()
    data class OnDescriptionChange(val description: String): AddBookEvent()
    object OnAddBookClick: AddBookEvent()
}
