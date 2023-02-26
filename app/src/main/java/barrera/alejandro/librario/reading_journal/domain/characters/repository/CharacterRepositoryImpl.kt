package barrera.alejandro.librario.reading_journal.domain.characters.repository

import barrera.alejandro.librario.reading_journal.data.characters.dao.CharacterDao
import barrera.alejandro.librario.reading_journal.data.characters.entity.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : CharacterRepository {
    override fun getBookCharacters(bookId: Int): Flow<List<Character>> {
        return characterDao.getBookCharacters(bookId)
    }

    override suspend fun insertCharacter(character: Character) {
        characterDao.insertCharacter(character)
    }

    override suspend fun updateCharacter(
        name: String,
        description: String,
        portraitTag: String,
        characterId: Int
    ) {
        characterDao.updateCharacter(name, description, portraitTag, characterId)
    }

    override suspend fun deleteCharacter(characterId: Int) {
        characterDao.deleteCharacter(characterId)
    }
}