package barrera.alejandro.librario.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import barrera.alejandro.librario.core.presentation.navigation.NavigationScreen
import barrera.alejandro.librario.core.presentation.navigation.NavigationScreen.*

@Composable
fun BottomBar(
    navController: NavController,
    bottomBarState: Boolean,
    currentDestination: NavDestination?
) {
    val screens = listOf(BooksScreen, ExploreScreen, SettingsScreen)

    AnimatedVisibility(
        visible = bottomBarState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        NavigationBar(
            containerColor = colorScheme.primary,
            contentColor = colorScheme.secondary
        ) {
            screens.forEach { screen ->
                val screenIsActive = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                }

                NavigationBarItem(
                    label = {
                        Text(
                            text = stringResource(screen.iconLabelId!!),
                            fontWeight = if (screenIsActive == true) FontWeight.Bold else FontWeight.Medium
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.iconImageId!!),
                            contentDescription = stringResource(id = screen.iconImageDescription!!)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorScheme.onSecondary,
                        selectedTextColor = colorScheme.onPrimary,
                        indicatorColor = colorScheme.secondary,
                        unselectedIconColor = colorScheme.onPrimary,
                        unselectedTextColor = colorScheme.onPrimary
                    ),
                    selected = screenIsActive ?: false,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(BooksScreen.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}