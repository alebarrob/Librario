package barrera.alejandro.librario.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import barrera.alejandro.librario.books.presentation.book.AddBookScreen
import barrera.alejandro.librario.core.presentation.navigation.NavigationScreen.*
import barrera.alejandro.librario.views.books.screens.*
import barrera.alejandro.librario.explore.presentation.ExploreScreen
import barrera.alejandro.librario.settings.presentation.AuthorScreen
import barrera.alejandro.librario.settings.presentation.SettingsScreen
import barrera.alejandro.librario.settings.presentation.TermsAndConditionsScreen
import barrera.alejandro.librario.welcome.presentation.WelcomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    landscapeOrientation: Boolean,
    onCharacterBookIdForRoomInsertionChange: (Int) -> Unit
) {
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
                navController = navController
            )
        }
        composable(route = AddBookScreen.route) {
            AddBookScreen(
                landscapeOrientation = landscapeOrientation,
                paddingValues = paddingValues,
                navController = navController
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
                navController = navController,
                onCharacterBookIdForRoomInsertionChange = {
                    onCharacterBookIdForRoomInsertionChange(it)
                }
            )
        }
        composable(
            route = CharacterScreen.route,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) {
            CharactersScreen(
                paddingValues = paddingValues,
                landscapeOrientation = landscapeOrientation,
                navController = navController
            )
        }
        composable(
            route = CharacterDetailScreen.route,
            arguments = listOf(
                navArgument("characterId") { type = NavType.IntType },
                navArgument("characterName") { type = NavType.StringType },
                navArgument("characterDescription") { type = NavType.StringType },
                navArgument("characterPortrait") { type = NavType.StringType },
            )
        ) {
            CharacterDetailScreen(
                paddingValues = paddingValues,
                landscapeOrientation = landscapeOrientation,
                navController = navController
            )
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
        composable(
            route = BookNotesScreen.route,
            arguments = listOf(
                navArgument("bookId") { type = NavType.IntType },
                navArgument("bookNotes") { type = NavType.StringType }
            ),
        ) {
            BookNotesScreen(
                paddingValues = paddingValues,
                navController = navController
            )
        }
        composable(
            route = AddCharacterScreen.route,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) {
            AddCharacterScreen(
                paddingValues = paddingValues,
                navController = navController,
                landscapeOrientation = landscapeOrientation
            )
        }
        composable(
            route = ChangeCharacterColorScreen.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) {
            ChangeCharacterColorScreen(
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
                onClick = { destinationScreen ->
                    navController.navigate(destinationScreen!!.route)
                },
                paddingValues = paddingValues
            )
        }
        composable(route = AuthorScreen.route) {
            AuthorScreen(paddingValues = paddingValues)
        }
        composable(route = TermsAndConditionsScreen.route) {
            TermsAndConditionsScreen(paddingValues = paddingValues)
        }
    }
}