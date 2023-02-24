package barrera.alejandro.librario.core.presentation

sealed class CoreEvent {
    object ShowTopBar: CoreEvent()
    object HideTopBar: CoreEvent()
    object ShowBottomBar: CoreEvent()
    object HideBottomBar: CoreEvent()
    object ShowFloatingButton: CoreEvent()
    object HideFloatingButton: CoreEvent()
}