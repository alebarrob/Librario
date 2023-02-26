package barrera.alejandro.librario.reading_journal.domain.characters.use_case

data class CharactersUseCases(
    val insertCharacter: InsertCharacter,
    val getBookCharacters: GetBookCharacters,
    val updateCharacter: UpdateCharacter,
    val deleteCharacter: DeleteCharacter,
    val getPortraitPainterId: GetPortraitPainterId
)
