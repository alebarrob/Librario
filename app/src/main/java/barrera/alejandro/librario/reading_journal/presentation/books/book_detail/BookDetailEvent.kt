package barrera.alejandro.librario.reading_journal.presentation.books.book_detail

sealed class BookDetailEvent {
    data class OnTitleChange(val title: String): BookDetailEvent()
    data class OnAuthorChange(val author: String): BookDetailEvent()
    data class OnDescriptionChange(val description: String): BookDetailEvent()
    object OnSaveChangesClick: BookDetailEvent()
    object OnDeleteBookConfirmation: BookDetailEvent()
}
