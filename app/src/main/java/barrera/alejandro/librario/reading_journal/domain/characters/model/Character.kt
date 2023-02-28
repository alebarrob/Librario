package barrera.alejandro.librario.reading_journal.domain.characters.model

data class Character(
    val id: Int,

    val bookId: Int,

    val name: String,

    val description: String,

    val portraitTag: String,
)
