package barrera.alejandro.librario.reading_journal.presentation.characters.characters_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.reading_journal.domain.characters.use_case.CharactersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersOverviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val charactersUseCases: CharactersUseCases
) : ViewModel() {
    var state by mutableStateOf(CharactersOverviewState(
        bookId = savedStateHandle.get<Int>("bookId")!!
    ))
        private set

    fun onEvent(event: CharactersOverviewEvent) {
        when (event) {
            is CharactersOverviewEvent.LoadCharacters -> {
                loadCharacters()
            }
            is CharactersOverviewEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            is CharactersOverviewEvent.OnSearch -> {
                loadCharacters()
            }
            is CharactersOverviewEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            charactersUseCases.getBookCharacters(
                bookId = state.bookId,
                query = state.query
            ).collect { loadedCharacters ->
                state = state.copy(characterEntities = loadedCharacters)
            }
        }
    }

    fun getPortraitPainterId(portraitTag: String): Int {
        return charactersUseCases.getPortraitPainterId(portraitTag)
    }
}