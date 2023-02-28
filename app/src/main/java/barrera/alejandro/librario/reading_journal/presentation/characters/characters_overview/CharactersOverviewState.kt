package barrera.alejandro.librario.reading_journal.presentation.characters.characters_overview

import barrera.alejandro.librario.reading_journal.data.characters.entity.CharacterEntity

data class CharactersOverviewState(
    val bookId: Int,
    val query: String = "",
    val isHintVisible: Boolean = false,
    val characterEntities: List<CharacterEntity> = emptyList()
)