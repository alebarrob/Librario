package barrera.alejandro.librario.reading_journal.data.characters.repository

import barrera.alejandro.librario.reading_journal.data.characters.dao.CharacterDao
import barrera.alejandro.librario.reading_journal.domain.characters.model.Character
import barrera.alejandro.librario.reading_journal.data.characters.mapper.toCharacter
import barrera.alejandro.librario.reading_journal.data.characters.mapper.toCharacterEntity
import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : CharacterRepository {
    override suspend fun insertCharacter(character: Character) {
        characterDao.insertCharacter(character.toCharacterEntity())
    }

    override fun getBookCharacters(bookId: Int): Flow<List<Character>> {
        return characterDao.getBookCharacters(bookId).map { entities ->
            entities.map { it.toCharacter() }
        }
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