package barrera.alejandro.librario.views.commonui

import android.content.res.Configuration
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import barrera.alejandro.librario.models.routes.ScreenNavigation.*
import barrera.alejandro.librario.viewmodels.books.AddBookScreenViewModel
import barrera.alejandro.librario.viewmodels.books.BooksScreenViewModel
import barrera.alejandro.librario.views.books.*
import barrera.alejandro.librario.views.books.composables.AddBookButton
import barrera.alejandro.librario.views.books.screens.*
import barrera.alejandro.librario.views.explore.ExploreScreen
import barrera.alejandro.librario.views.settings.TermsAndConditionsScreen
import barrera.alejandro.librario.views.settings.screens.AuthorScreen
import barrera.alejandro.librario.views.settings.screens.SettingsScreen
import barrera.alejandro.librario.views.welcome.WelcomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibrarioApp(
    navController: NavHostController,
    currentDestination: NavDestination?,
    topBarState: Boolean,
    bottomBarState: Boolean,
    backButtonState: Boolean,
    floatingActionButtonState: Boolean,
    searchButtonState: Boolean,
    booksScreenViewModel: BooksScreenViewModel,
    addBookScreenViewModel: AddBookScreenViewModel
) {
    val context = LocalContext.current
    val landscapeOrientation = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        topBar = {
            LibrarioTopBar(
                navController = navController,
                topBarState = topBarState,
                backButtonState = backButtonState,
                searchButtonState = searchButtonState
            )
        },
        bottomBar = {
            LibrarioBottomBar(
                navController = navController,
                bottomBarState = bottomBarState,
                currentDestination = currentDestination
            )
        },
        floatingActionButton = {
            AddBookButton(
                onClickAddBookButton = {
                    navController.navigate(route = "addBookScreen")
                },
            addBookButtonState = floatingActionButtonState,
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
                BooksScreen(
                    paddingValues = paddingValues,
                    landscapeOrientation = landscapeOrientation,
                    navController = navController,
                    booksScreenViewModel = booksScreenViewModel
                )
            }
            composable(route = AddBookScreen.route) {
                AddBookScreen(
                    landscapeOrientation = landscapeOrientation,
                    context = context,
                    paddingValues = paddingValues,
                    navController = navController,
                    addBookScreenViewModel = addBookScreenViewModel
                )
            }
            composable(
                route = BookDetailScreen.route,
                arguments = listOf(
                    navArgument("bookId") { type = NavType.IntType },
                    navArgument("bookTitle") { type = NavType.StringType },
                    navArgument("bookAuthor") { type = NavType.StringType },
                    navArgument("bookDescription") { type = NavType.StringType }
                )
            ) {
                BookDetailScreen(
                    landscapeOrientation = landscapeOrientation,
                    paddingValues = paddingValues,
                    context = context,
                    navController = navController,
                )
            }
            composable(route = CharactersScreen.route) {
                CharactersScreen(paddingValues = paddingValues)
            }
            composable(route = CharacterDetailScreen.route) {
                CharacterDetailScreen(paddingValues = paddingValues)
            }
            composable(
                route = ChangeBookColorScreen.route,
                arguments = listOf(navArgument("bookId") { type = NavType.IntType })
            ) {
                ChangeBookColorScreen(
                    paddingValues = paddingValues,
                    navController = navController
                )
            }

            //Explore Feature
            composable(route = ExploreScreen.route) {
                ExploreScreen(paddingValues = paddingValues)
            }

            //Settings Feature
            composable(route = SettingsScreen.route) {
                SettingsScreen(
                    landscapeOrientation = landscapeOrientation,
                    onClickOption = { destinationScreen ->
                        navController.navigate(destinationScreen!!.route)
                    },
                    paddingValues = paddingValues
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