package barrera.alejandro.librario.reading_journal.presentation.characters.characters_overview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import barrera.alejandro.librario.reading_journal.data.characters.entity.Character
import barrera.alejandro.librario.reading_journal.domain.characters.use_case.CharactersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersOverviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val charactersUseCases: CharactersUseCases
) : ViewModel() {
    val characters: Flow<List<Character>> = charactersUseCases.getBookCharacters(
        savedStateHandle.get<Int>("bookId")!!
    )

    fun getPortraitPainterId(portraitTag: String): Int {
        return charactersUseCases.getPortraitPainterId(portraitTag)
    }
}