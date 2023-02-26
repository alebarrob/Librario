package barrera.alejandro.librario.reading_journal.domain.characters.use_case

import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository

class DeleteCharacter(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(characterId: Int) {
        characterRepository.deleteCharacter(characterId)
    }
}