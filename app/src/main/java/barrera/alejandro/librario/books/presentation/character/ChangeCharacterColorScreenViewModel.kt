package barrera.alejandro.librario.books.presentation.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.books.data.character.repository.CharacterRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeCharacterColorScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterRepositoryImpl: CharacterRepositoryImpl
) : ViewModel() {
    private val _characterId = MutableStateFlow(savedStateHandle.get<Int>("characterId")!!)
    val characterId: Flow<Int> get() = _characterId

    fun changeColor(characterColor: String, characterId: Int) {
        viewModelScope.launch {
            characterRepositoryImpl.changeColor(characterColor, characterId)
        }
    }
}