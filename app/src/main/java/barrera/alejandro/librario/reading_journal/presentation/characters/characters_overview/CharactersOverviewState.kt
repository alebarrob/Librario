package barrera.alejandro.librario.reading_journal.presentation.characters.characters_overview

import barrera.alejandro.librario.reading_journal.domain.characters.model.Character

data class CharactersOverviewState(
    val bookId: Int,
    val query: String = "",
    val isHintVisible: Boolean = false,
    val characters: List<Character> = emptyList()
)