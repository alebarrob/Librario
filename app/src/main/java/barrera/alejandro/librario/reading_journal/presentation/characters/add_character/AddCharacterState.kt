package barrera.alejandro.librario.reading_journal.presentation.characters.add_character

data class AddCharacterState(
    val bookId: Int,
    val name: String = "",
    val description: String = "",
    val portraitTag: String = "Mujer"
)
