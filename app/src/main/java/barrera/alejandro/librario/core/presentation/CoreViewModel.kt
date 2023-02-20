package barrera.alejandro.librario.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class CoreViewModel : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set

    fun onEvent(event: UiEvent) {
        uiState = when (event) {
            is UiEvent.ShowTopBar -> {
                uiState.copy(isTopBarVisible = true)
            }
            is UiEvent.HideTopBar -> {
                uiState.copy(isTopBarVisible = false)
            }
            is UiEvent.ShowBottomBar -> {
                uiState.copy(isBottomBarVisible = true)
            }
            is UiEvent.HideBottomBar -> {
                uiState.copy(isBottomBarVisible = false)
            }
            is UiEvent.ShowFloatingButton -> {
                uiState.copy(isFloatingButtonVisible = true)
            }
            is UiEvent.HideFloatingButton -> {
                uiState.copy(isFloatingButtonVisible = false)
            }
        }
    }
}