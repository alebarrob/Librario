package barrera.alejandro.librario.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CoreViewModel : ViewModel() {
    var state by mutableStateOf(CoreState())
        private set

    fun onEvent(event: CoreEvent) {
        state = when (event) {
            is CoreEvent.ShowTopBar -> {
                state.copy(isTopBarVisible = true)
            }
            is CoreEvent.HideTopBar -> {
                state.copy(isTopBarVisible = false)
            }
            is CoreEvent.ShowBottomBar -> {
                state.copy(isBottomBarVisible = true)
            }
            is CoreEvent.HideBottomBar -> {
                state.copy(isBottomBarVisible = false)
            }
            is CoreEvent.ShowFloatingButton -> {
                state.copy(isFloatingButtonVisible = true)
            }
            is CoreEvent.HideFloatingButton -> {
                state.copy(isFloatingButtonVisible = false)
            }
        }
    }
}