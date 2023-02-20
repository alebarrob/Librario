package barrera.alejandro.librario.books.presentation.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.books.data.character.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterRepositoryImpl: CharacterRepository
) : ViewModel() {
    private val _characterId = MutableStateFlow(savedStateHandle.get<Int>("characterId")!!)
    val characterId: Flow<Int> get() = _characterId

    private val _characterName = MutableStateFlow(savedStateHandle.get<String>("characterName")!!)
    val characterName: Flow<String> get() = _characterName

    private val _characterDescription = MutableStateFlow(savedStateHandle.get<String>("characterDescription")!!)
    val characterDescription: Flow<String> get() = _characterDescription

    private val _characterPortrait = MutableStateFlow(savedStateHandle.get<String>("characterPortrait")!!)
    val characterPortrait: Flow<String> get() = _characterPortrait

    val characterColor: Flow<String> = characterRepositoryImpl.getCharacterColor(_characterId.value)

    fun onCharacterNameChange(characterName: String) {
        _characterName.value = characterName
    }

    fun onCharacterDescriptionChange(characterDescription: String) {
        _characterDescription.value = characterDescription
    }

    fun onCharacterPortraitChange(characterPortrait: String) {
        _characterPortrait.value = characterPortrait
    }

    fun updateCharacter(characterName: String, characterDescription: String, characterPortrait: String, characterId: Int) {
        viewModelScope.launch {
            characterRepositoryImpl.updateCharacter(characterName, characterDescription, characterPortrait, characterId)
        }
    }

    fun deleteCharacter(characterId: Int) {
        viewModelScope.launch {
            characterRepositoryImpl.deleteCharacter(characterId)
        }
    }
}