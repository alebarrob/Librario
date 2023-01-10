package barrera.alejandro.librario.views.commonui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import barrera.alejandro.librario.models.routes.ScreenNavigation.*
import barrera.alejandro.librario.views.books.*
import barrera.alejandro.librario.views.explore.ExploreScreen
import barrera.alejandro.librario.views.settings.AuthorScreen
import barrera.alejandro.librario.views.settings.SettingsScreen
import barrera.alejandro.librario.views.settings.TermsAndConditionsScreen
import barrera.alejandro.librario.views.welcome.WelcomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibrarioApp(
    navController: NavHostController,
    currentDestination: NavDestination?,
    topBarState: MutableState<Boolean>,
    bottomBarState: MutableState<Boolean>,
    backButtonState: MutableState<Boolean>,
    floatingActionButtonState: MutableState<Boolean>
    ) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val screens = listOf(BooksScreen, ExploreScreen, SettingsScreen)

    Scaffold(
        topBar = {
            LibrarioTopBar(
                navController = navController,
                topBarState = topBarState,
                backButtonState = backButtonState
            )
        },
        bottomBar = {
            LibrarioBottomBar(
                navController = navController,
                bottomBarState = bottomBarState,
                currentDestination = currentDestination,
                screens = screens
            )
        },
        floatingActionButton = {
            LibrarioFloatingActionButton(
                onClickFloatingActionButton = {
                    navController.navigate(BookDetailScreen.route)
                },
            floatingActionButtonState = floatingActionButtonState,
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = WelcomeScreen.route
        ) {
            // Welcome Feature
            composable(route = WelcomeScreen.route) {
                WelcomeScreen(
                    navigation = {
                        navController.navigate(BooksScreen.route) {
                            popUpTo(WelcomeScreen.route) { inclusive = true }
                        }
                    }
                )
            }

            // Books Feature
            composable(route = BooksScreen.route) {
                BooksScreen(paddingValues = paddingValues)
            }
            composable(route = BookDetailScreen.route) {
                BookDetailScreen(paddingValues = paddingValues)
            }
            composable(route = CharactersScreen.route) {
                CharactersScreen(paddingValues = paddingValues)
            }
            composable(route = CharacterDetailScreen.route) {
                CharacterDetailScreen(paddingValues = paddingValues)
            }

            //Explore Feature
            composable(route = ExploreScreen.route) {
                ExploreScreen(paddingValues = paddingValues)
            }

            //Settings Feature
            composable(route = SettingsScreen.route) {
                SettingsScreen(
                    onClickSettingsOption = { destinationScreen ->
                        navController.navigate(destinationScreen.route)
                    },
                    paddingValues = paddingValues,
                    configuration = configuration
                )
            }
            composable(route = AuthorScreen.route) {
                AuthorScreen(
                    paddingValues = paddingValues,
                    context = context
                )
            }
            composable(route = TermsAndConditionsScreen.route) {
                TermsAndConditionsScreen(
                    paddingValues = paddingValues,
                    context = context
                )
            }
        }
    }
}