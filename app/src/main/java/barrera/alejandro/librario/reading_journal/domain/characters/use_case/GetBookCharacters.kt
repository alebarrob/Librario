package barrera.alejandro.librario.reading_journal.domain.characters.use_case

import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository
import barrera.alejandro.librario.reading_journal.domain.characters.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBookCharacters(private val characterRepository: CharacterRepository) {
    operator fun invoke(
        bookId: Int,
        query: String
    ): Flow<List<Character>> {
        return if (query.isBlank()) {
            characterRepository.getBookCharacters(bookId)
        } else {
            characterRepository.getBookCharacters(bookId).map { characters ->
                characters.filter { character ->
                    character.name.trim().contains(query, ignoreCase = true)
                }
            }
        }
    }
}