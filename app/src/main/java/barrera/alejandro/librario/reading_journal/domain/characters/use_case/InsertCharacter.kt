package barrera.alejandro.librario.reading_journal.domain.characters.use_case

import barrera.alejandro.librario.reading_journal.data.characters.entity.CharacterEntity
import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository

class InsertCharacter(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(characterEntity: CharacterEntity) {
        characterRepository.insertCharacter(characterEntity)
    }
}