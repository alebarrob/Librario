package barrera.alejandro.librario.views.commonui

import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import barrera.alejandro.librario.models.routes.ScreenNavigation

@Composable
fun SalvaIdeasBottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
    screens: List<ScreenNavigation>
) {
    NavigationBar(
        containerColor = colorScheme.primary,
        contentColor = colorScheme.secondary
    ) {
        screens.forEach { screen ->
            val screenIsActive = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                selected = screenIsActive,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.iconImageId),
                        contentDescription = stringResource(id = screen.iconImageDescription)
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.iconLabelId),
                        fontWeight = if (screenIsActive) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorScheme.onSecondary,
                    selectedTextColor = colorScheme.onPrimary,
                    indicatorColor = colorScheme.secondary,
                    unselectedIconColor = colorScheme.onPrimary,
                    unselectedTextColor = colorScheme.onPrimary
                )
            )
        }
    }
}