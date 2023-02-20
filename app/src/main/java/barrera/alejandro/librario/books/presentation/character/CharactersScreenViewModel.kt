package barrera.alejandro.librario.books.presentation.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import barrera.alejandro.librario.books.data.character.entity.Character
import barrera.alejandro.librario.books.data.character.repository.CharacterRepository
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