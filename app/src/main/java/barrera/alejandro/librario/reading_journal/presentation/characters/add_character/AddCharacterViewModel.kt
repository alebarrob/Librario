package barrera.alejandro.librario.reading_journal.presentation.characters.add_character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.domain.use_case.CoreUseCases
import barrera.alejandro.librario.core.util.UiEvent
import barrera.alejandro.librario.core.util.UiText
import barrera.alejandro.librario.core.domain.use_case.ValidateInfoNotEmpty
import barrera.alejandro.librario.reading_journal.data.characters.entity.Character
import barrera.alejandro.librario.reading_journal.domain.characters.use_case.CharactersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCharacterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val coreUseCases: CoreUseCases,
    private val charactersUseCases: CharactersUseCases
) : ViewModel() {
    var state by mutableStateOf(AddCharacterState(
        bookId = savedStateHandle.get<Int>("bookId")!!
    ))
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddCharacterEvent) {
        when (event) {
            is AddCharacterEvent.OnNameChange -> {
                state = state.copy(name = event.name)
            }
            is AddCharacterEvent.OnDescriptionChange -> {
                state = state.copy(description = event.description)
            }
            is AddCharacterEvent.OnPortraitTagChange -> {
                state = state.copy(portraitTag = event.portraitTag)
            }
            is AddCharacterEvent.OnAddCharacterClick -> {
                val result = coreUseCases.validateInfoNotEmpty(
                    listOf(state.name, state.description)
                )

                when (result) {
                    is ValidateInfoNotEmpty.Result.Success -> {
                        viewModelScope.launch {
                            charactersUseCases.insertCharacter(
                                Character(
                                    id = 0,
                                    bookId = state.bookId,
                                    name = state.name,
                                    description = state.description,
                                    portraitTag = state.portraitTag
                                )
                            )
                            _uiEvent.send(UiEvent.NavigateUp)
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.add_character_success)
                                )
                            )
                        }
                    }
                    is ValidateInfoNotEmpty.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.add_character_error)
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}