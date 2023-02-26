package barrera.alejandro.librario.reading_journal.domain.characters.use_case

import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository

class UpdateCharacter(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(
        name: String,
        description: String,
        portraitTag: String,
        characterId: Int
    ) {
        characterRepository.updateCharacter(name, description, portraitTag, characterId)
    }
}