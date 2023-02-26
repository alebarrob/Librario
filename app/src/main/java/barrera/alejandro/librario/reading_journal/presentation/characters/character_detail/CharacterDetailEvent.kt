package barrera.alejandro.librario.reading_journal.presentation.characters.character_detail

sealed class CharacterDetailEvent {
    data class OnNameChange(val name: String): CharacterDetailEvent()
    data class OnDescriptionChange(val description: String): CharacterDetailEvent()
    data class OnPortraitTagChange(val portraitTag: String): CharacterDetailEvent()
    object OnSaveChangesClick: CharacterDetailEvent()
    object OnDeleteCharacterConfirmation: CharacterDetailEvent()
}