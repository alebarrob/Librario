package barrera.alejandro.librario.books.presentation.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.books.data.character.entity.Character
import barrera.alejandro.librario.books.data.character.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCharacterScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterRepositoryImpl: CharacterRepository
) : ViewModel() {
    private val _bookId = MutableStateFlow(savedStateHandle.get<Int>("bookId")!!)
    val bookId: Flow<Int> get() = _bookId

    fun insertCharacter(character: Character) {
        viewModelScope.launch {
            characterRepositoryImpl.insertCharacter(character)
        }
    }
}