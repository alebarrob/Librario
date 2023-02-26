package barrera.alejandro.librario.reading_journal.presentation.characters.character_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.domain.use_case.CoreUseCases
import barrera.alejandro.librario.core.domain.use_case.ValidateInfoNotEmpty
import barrera.alejandro.librario.core.util.UiEvent
import barrera.alejandro.librario.core.util.UiText
import barrera.alejandro.librario.reading_journal.domain.characters.use_case.CharactersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val coreUseCases: CoreUseCases,
    private val charactersUseCases: CharactersUseCases
) : ViewModel() {
    var state by mutableStateOf(
        CharacterDetailState(
            characterId = savedStateHandle.get<Int>("characterId")!!,
            name = savedStateHandle.get<String>("name")!!,
            description = savedStateHandle.get<String>("description")!!,
            portraitTag = savedStateHandle.get<String>("portraitTag")!!
        )
    )
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: CharacterDetailEvent) {
        when (event) {
            is CharacterDetailEvent.OnNameChange -> {
                state = state.copy(name = event.name)
            }
            is CharacterDetailEvent.OnDescriptionChange -> {
                state = state.copy(description = event.description)
            }
            is CharacterDetailEvent.OnPortraitTagChange -> {
                state = state.copy(portraitTag = event.portraitTag)
            }
            is CharacterDetailEvent.OnSaveChangesClick -> {
                val result = coreUseCases.validateInfoNotEmpty(
                    listOf(state.name, state.description)
                )

                when (result) {
                    is ValidateInfoNotEmpty.Result.Success -> {
                        viewModelScope.launch {
                            charactersUseCases.updateCharacter(
                                name = state.name,
                                description = state.description,
                                portraitTag = state.portraitTag,
                                characterId = state.characterId
                            )
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.update_character_success)
                                )
                            )
                        }
                    }
                    is ValidateInfoNotEmpty.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.update_character_error)
                                )
                            )
                        }
                    }
                }
            }
            is CharacterDetailEvent.OnDeleteCharacterConfirmation -> {
                viewModelScope.launch {
                    charactersUseCases.deleteCharacter(state.characterId)
                    _uiEvent.send(UiEvent.NavigateUp)
                    _uiEvent.send(
                        UiEvent.ShowToast(
                            UiText.StringResource(R.string.delete_character_confirmation)
                        )
                    )
                }
            }
        }
    }
}