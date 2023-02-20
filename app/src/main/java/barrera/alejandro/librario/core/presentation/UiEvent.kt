package barrera.alejandro.librario.core.presentation

sealed class UiEvent {
    object ShowTopBar: UiEvent()
    object HideTopBar: UiEvent()
    object ShowBottomBar: UiEvent()
    object HideBottomBar: UiEvent()
    object ShowFloatingButton: UiEvent()
    object HideFloatingButton: UiEvent()
}