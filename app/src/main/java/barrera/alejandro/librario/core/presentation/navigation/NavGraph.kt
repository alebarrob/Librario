package barrera.alejandro.librario.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import barrera.alejandro.librario.core.presentation.navigation.NavigationScreen.*
import barrera.alejandro.librario.explore.presentation.ExploreScreen
import barrera.alejandro.librario.reading_journal.presentation.books.add_book.AddBookScreen
import barrera.alejandro.librario.reading_journal.presentation.books.book_detail.BookDetailScreen
import barrera.alejandro.librario.reading_journal.presentation.books.book_notes.BookNotesScreen
import barrera.alejandro.librario.reading_journal.presentation.books.books_overview.BooksOverviewScreen
import barrera.alejandro.librario.reading_journal.presentation.characters.add_character.AddCharacterScreen
import barrera.alejandro.librario.reading_journal.presentation.characters.character_detail.CharacterDetailScreen
import barrera.alejandro.librario.reading_journal.presentation.characters.characters_overview.CharactersScreen
import barrera.alejandro.librario.settings.presentation.AuthorScreen
import barrera.alejandro.librario.settings.presentation.SettingsScreen
import barrera.alejandro.librario.settings.presentation.TermsAndConditionsScreen
import barrera.alejandro.librario.welcome.presentation.WelcomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = WelcomeScreen.route
    ) {
        // Welcome Feature
        composable(route = WelcomeScreen.route) {
            WelcomeScreen(
                onNavigateToBooksOverview = {
                    navController.navigate(BooksOverviewScreen.route) {
                        popUpTo(WelcomeScreen.route) { inclusive = true }
                    }
                }
            )
        }

        // Books Feature
        composable(route = BooksOverviewScreen.route) {
            BooksOverviewScreen(
                paddingValues = paddingValues,
                onNavigateToBookDetail = { bookId, title, author, description ->
                    navController.navigate(
                        BookDetailScreen.route + "/$bookId" +
                                "/$title" +
                                "/$author" +
                                "/$description"
                    )
                }
            )
        }
        composable(route = AddBookScreen.route) {
            AddBookScreen(
                paddingValues = paddingValues,
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = BookDetailScreen.route + "/{bookId}/{title}/{author}/{description}",
            arguments = listOf(
                navArgument("bookId") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType },
                navArgument("author") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) {
            BookDetailScreen(
                paddingValues = paddingValues,
                onNavigateToNotes = { bookId ->
                    navController.navigate(
                        BookNotesScreen.route + "/$bookId"
                    )
                },
                /*TODO()*/
                onNavigateToCharacters = { bookId ->
                    navController.navigate(
                        CharacterScreen.route + "/$bookId"
                    )
                },
                /*TODO()*/
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = BookNotesScreen.route + "/{bookId}",
            arguments = listOf(
                navArgument("bookId") { type = NavType.IntType }
            ),
        ) {
            BookNotesScreen(
                paddingValues = paddingValues,
                onNavigateUp = { navController.navigateUp() }
            )
        }
        /*TODO()*/
        composable(
            route = CharacterScreen.route,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) {
            CharactersScreen(
                paddingValues = paddingValues,
                navController = navController
            )
        }
        composable(
            route = CharacterDetailScreen.route,
            arguments = listOf(
                navArgument("characterId") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("portrait") { type = NavType.StringType }
            )
        ) {
            CharacterDetailScreen(
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
                navController = navController
            )
        }
        /*TODO()*/

        //Explore Feature
        composable(route = ExploreScreen.route) {
            ExploreScreen(paddingValues = paddingValues)
        }

        //Settings Feature
        composable(route = SettingsScreen.route) {
            SettingsScreen(
                paddingValues = paddingValues,
                onNavigate = { route ->
                    navController.navigate(route)
                }
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