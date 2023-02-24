package barrera.alejandro.librario.reading_journal.domain.books.use_case

data class BooksUseCases(
    val getAllBooks: GetAllBooks,
    val updateBook: UpdateBook,
    val insertBook: InsertBook,
    val deleteBook: DeleteBook,
    val getBookNotes: GetBookNotes,
    val updateBookNotes: UpdateBookNotes,
    val validateBookInfo: ValidateBookInfo,
    val validateBookNotes: ValidateBookNotes
)
