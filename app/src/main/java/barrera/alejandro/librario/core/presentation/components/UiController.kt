package barrera.alejandro.librario.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import barrera.alejandro.librario.core.presentation.CoreViewModel
import barrera.alejandro.librario.core.presentation.CoreEvent

@Composable
fun UiController(
    viewModel: CoreViewModel,
    currentDestination: NavDestination?
) {
    TopBarController(currentDestination = currentDestination, viewModel = viewModel)
    BottomBarController(currentDestination = currentDestination, viewModel = viewModel)
    FloatingButtonController(currentDestination = currentDestination, viewModel = viewModel)
}

@Composable
fun TopBarController(
    viewModel: CoreViewModel,
    currentDestination: NavDestination?
) {
    when (currentDestination?.route) {
        "books", "exploreBooks", "settings", "welcome" -> viewModel.onEvent(CoreEvent.HideTopBar)
        else -> viewModel.onEvent(CoreEvent.ShowTopBar)
    }
}

@Composable
fun BottomBarController(
    viewModel: CoreViewModel,
    currentDestination: NavDestination?
) {
    when (currentDestination?.route) {
        "books", "exploreBooks", "settings" -> viewModel.onEvent(CoreEvent.ShowBottomBar)
        else -> viewModel.onEvent(CoreEvent.HideBottomBar)
    }
}

@Composable
fun FloatingButtonController(
    viewModel: CoreViewModel,
    currentDestination: NavDestination?
) {
    when (currentDestination?.route) {
        "books", "characters" + "/{bookId}" -> viewModel.onEvent(CoreEvent.ShowFloatingButton)
        else -> viewModel.onEvent(CoreEvent.HideFloatingButton)
    }
}