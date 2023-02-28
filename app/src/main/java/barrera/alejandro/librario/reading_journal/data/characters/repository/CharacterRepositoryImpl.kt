package barrera.alejandro.librario.reading_journal.data.characters.repository

import barrera.alejandro.librario.reading_journal.data.characters.dao.CharacterDao
import barrera.alejandro.librario.reading_journal.data.characters.entity.CharacterEntity
import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : CharacterRepository {
    override fun getBookCharacters(bookId: Int): Flow<List<CharacterEntity>> {
        return characterDao.getBookCharacters(bookId)
    }

    override suspend fun insertCharacter(characterEntity: CharacterEntity) {
        characterDao.insertCharacter(characterEntity)
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