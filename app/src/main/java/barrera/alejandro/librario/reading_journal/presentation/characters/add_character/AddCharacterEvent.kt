package barrera.alejandro.librario.reading_journal.presentation.characters.add_character

sealed class AddCharacterEvent {
    data class OnNameChange(val name: String): AddCharacterEvent()
    data class OnDescriptionChange(val description: String): AddCharacterEvent()
    data class OnPortraitTagChange(val portraitTag: String): AddCharacterEvent()
    object OnAddCharacterClick: AddCharacterEvent()
}
