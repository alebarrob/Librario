package barrera.alejandro.librario.reading_journal.presentation.characters.characters_overview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import barrera.alejandro.librario.reading_journal.data.characters.entity.Character
import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    characterRepositoryImpl: CharacterRepository
) : ViewModel() {
    val characters: Flow<List<Character>> = characterRepositoryImpl.getBookCharacters(savedStateHandle.get<Int>("bookId")!!)
}