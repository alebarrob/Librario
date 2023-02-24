package barrera.alejandro.librario.core.util

sealed class UiEvent {
    object NavigateUp: UiEvent()
    data class ShowToast(val message: UiText): UiEvent()
}
