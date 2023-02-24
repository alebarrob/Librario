package barrera.alejandro.librario.reading_journal.domain.characters.repository

import barrera.alejandro.librario.reading_journal.data.characters.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getBookCharacters(bookId: Int): Flow<List<Character>>
    fun getCharacterPortrait(characterId: Int): Flow<String>
    suspend fun insertCharacter(character: Character)
    suspend fun updateCharacter(
        characterName: String,
        characterDescription: String,
        characterPortrait: String,
        characterId: Int
    )
    suspend fun deleteCharacter(characterId: Int)
}