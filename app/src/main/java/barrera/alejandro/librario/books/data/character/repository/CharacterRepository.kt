package barrera.alejandro.librario.books.data.character.repository

import barrera.alejandro.librario.books.data.character.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getBookCharacters(bookId: Int): Flow<List<Character>>

    fun getCharacterColor(characterId: Int): Flow<String>

    fun getCharacterPortrait(characterId: Int): Flow<String>

    suspend fun insertCharacter(character: Character)

    suspend fun updateCharacter(characterName: String, characterDescription: String, characterPortrait: String, characterId: Int)

    suspend fun changeColor(characterColor: String, characterId: Int)

    suspend fun deleteCharacter(characterId: Int)
}