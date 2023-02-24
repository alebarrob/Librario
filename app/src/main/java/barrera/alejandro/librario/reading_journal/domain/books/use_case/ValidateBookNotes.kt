package barrera.alejandro.librario.reading_journal.domain.books.use_case

class ValidateBookNotes {
    operator fun invoke(notes: String): Result {
        return if (notes == "") Result.Error else Result.Success(notes)
    }

    sealed class Result {
        data class Success(val notes: String): Result()
        object Error: Result()
    }
}