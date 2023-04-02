package barrera.alejandro.librario.reading_journal.presentation.characters.characters_overview

sealed class CharactersOverviewEvent {
    data class OnQueryChange(val query: String): CharactersOverviewEvent()
    object OnSearch : CharactersOverviewEvent()
    object LoadCharacters: CharactersOverviewEvent()
}
