package barrera.alejandro.librario.reading_journal.presentation.books.book_notes

sealed class BookNotesEvent {
    data class OnNotesChange(val notes: String): BookNotesEvent()
    object OnSaveNotesClick: BookNotesEvent()
    object LoadNotes: BookNotesEvent()
}
