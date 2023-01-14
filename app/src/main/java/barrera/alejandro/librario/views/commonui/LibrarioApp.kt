package barrera.alejandro.librario.views.commonui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import barrera.alejandro.librario.models.routes.ScreenNavigation.*
import barrera.alejandro.librario.viewmodels.books.BookDetailScreenViewModel
import barrera.alejandro.librario.viewmodels.books.BooksScreenViewModel
import barrera.alejandro.librario.views.books.BookDetailScreen
import barrera.alejandro.librario.views.books.BooksScreen
import barrera.alejandro.librario.views.books.CharacterDetailScreen
import barrera.alejandro.librario.views.books.CharactersScreen
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
    topBarState: Boolean,
    bottomBarState: Boolean,
    backButtonState: Boolean,
    floatingActionButtonState: Boolean,
    searchButtonState: Boolean,
    bookDetailScreenViewModel: BookDetailScreenViewModel,
    booksScreenViewModel: BooksScreenViewModel
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    var bookOptionsState by rememberSaveable { (mutableStateOf(true)) }

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
        //snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        floatingActionButton = {
            AddBookButton(
                onClickAddBookButton = {
                    bookOptionsState = false
                    navController.navigate(BookDetailScreen.route)
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
                    booksScreenViewModel = booksScreenViewModel
                )
            }
            composable(route = BookDetailScreen.route) {
                BookDetailScreen(
                    configuration = configuration,
                    context = context,
                    paddingValues = paddingValues,
                    navController = navController,
                    bookOptionsState = bookOptionsState,
                    bookDetailScreenViewModel = bookDetailScreenViewModel
                )
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
                    configuration = configuration,
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