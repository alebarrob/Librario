package barrera.alejandro.librario.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import barrera.alejandro.librario.core.presentation.CoreViewModel
import barrera.alejandro.librario.core.presentation.UiEvent

@Composable
fun UiController(
    currentDestination: NavDestination?,
    viewModel: CoreViewModel
) {
    TopBarController(currentDestination = currentDestination, viewModel = viewModel)
    BottomBarController(currentDestination = currentDestination, viewModel = viewModel)
    FloatingButtonController(currentDestination = currentDestination, viewModel = viewModel)
}

@Composable
fun TopBarController(
    currentDestination: NavDestination?,
    viewModel: CoreViewModel
) {
    when (currentDestination?.route) {
        "books", "explore", "settings", "welcome" -> viewModel.onEvent(UiEvent.HideTopBar)
        else -> viewModel.onEvent(UiEvent.ShowTopBar)
    }
}

@Composable
fun BottomBarController(
    currentDestination: NavDestination?,
    viewModel: CoreViewModel
) {
    when (currentDestination?.route) {
        "books", "explore", "settings" -> viewModel.onEvent(UiEvent.ShowBottomBar)
        else -> viewModel.onEvent(UiEvent.HideBottomBar)
    }
}

@Composable
fun FloatingButtonController(
    currentDestination: NavDestination?,
    viewModel: CoreViewModel
) {
    when (currentDestination?.route) {
        "books", "characters/{bookId}" -> viewModel.onEvent(UiEvent.ShowFloatingButton)
        else -> viewModel.onEvent(UiEvent.HideFloatingButton)
    }
}