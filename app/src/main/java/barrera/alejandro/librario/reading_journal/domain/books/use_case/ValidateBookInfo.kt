package barrera.alejandro.librario.reading_journal.domain.books.use_case

class ValidateBookInfo {
    operator fun invoke(
        title: String,
        author: String,
        description: String
    ): Result {
        return if (title == "" || author == "" || description == "") {
            Result.Error
        } else {
            Result.Success(title, author, description)
        }
    }

    sealed class Result {
        data class Success(
            val title: String,
            val author: String,
            val description: String
        ): Result()
        object Error: Result()
    }
}