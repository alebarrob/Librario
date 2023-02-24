package barrera.alejandro.librario.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CoreViewModel : ViewModel() {
    var coreState by mutableStateOf(CoreState())
        private set

    fun onEvent(event: CoreEvent) {
        coreState = when (event) {
            is CoreEvent.ShowTopBar -> {
                coreState.copy(isTopBarVisible = true)
            }
            is CoreEvent.HideTopBar -> {
                coreState.copy(isTopBarVisible = false)
            }
            is CoreEvent.ShowBottomBar -> {
                coreState.copy(isBottomBarVisible = true)
            }
            is CoreEvent.HideBottomBar -> {
                coreState.copy(isBottomBarVisible = false)
            }
            is CoreEvent.ShowFloatingButton -> {
                coreState.copy(isFloatingButtonVisible = true)
            }
            is CoreEvent.HideFloatingButton -> {
                coreState.copy(isFloatingButtonVisible = false)
            }
        }
    }
}