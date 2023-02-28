package barrera.alejandro.librario.reading_journal.domain.characters.use_case

import barrera.alejandro.librario.reading_journal.domain.characters.model.Character
import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository

class InsertCharacter(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(character: Character) {
        characterRepository.insertCharacter(character)
    }
}