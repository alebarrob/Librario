package barrera.alejandro.librario.reading_journal.presentation.characters.add_character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.reading_journal.data.characters.entity.Character
import barrera.alejandro.librario.reading_journal.domain.characters.repository.CharacterRepository
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