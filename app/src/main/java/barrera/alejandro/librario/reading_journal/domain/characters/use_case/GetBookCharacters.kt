package barrera.alejandro.librario.reading_journal.domain.characters.use_case

import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository
import barrera.alejandro.librario.reading_journal.data.characters.entity.Character
import kotlinx.coroutines.flow.Flow

class GetBookCharacters(private val characterRepository: CharacterRepository) {
    operator fun invoke(bookId: Int): Flow<List<Character>> {
        return characterRepository.getBookCharacters(bookId)
    }
}