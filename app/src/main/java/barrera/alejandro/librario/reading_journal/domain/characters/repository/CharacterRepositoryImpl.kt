package barrera.alejandro.librario.reading_journal.domain.characters.repository

import barrera.alejandro.librario.reading_journal.data.characters.dao.CharacterDao
import barrera.alejandro.librario.reading_journal.data.characters.entity.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : CharacterRepository {

    override fun getBookCharacters(bookId: Int): Flow<List<Character>> = characterDao.getBookCharacters(bookId)
    override fun getCharacterPortrait(characterId: Int): Flow<String> = characterDao.getCharacterPortrait(characterId)
    override suspend fun insertCharacter(character: Character) = characterDao.insertCharacter(character)
    override suspend fun updateCharacter(
        characterName: String,
        characterDescription: String,
        characterPortrait: String,
        characterId: Int
    ) {
        characterDao.updateCharacter(
            characterName,
            characterDescription,
            characterPortrait,
            characterId
        )
    }
    override suspend fun deleteCharacter(characterId: Int) = characterDao.deleteCharacter(characterId)
}