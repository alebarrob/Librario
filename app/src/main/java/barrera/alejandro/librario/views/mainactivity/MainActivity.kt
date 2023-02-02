package barrera.alejandro.librario.views.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.librario.viewmodels.books.BooksScreenViewModel
import barrera.alejandro.librario.views.commonui.LibrarioApp
import barrera.alejandro.librario.views.theme.SalvaIdeasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SalvaIdeasTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val booksScreenViewModel = hiltViewModel<BooksScreenViewModel>()

                var floatingActionButtonState by rememberSaveable { (mutableStateOf(false)) }
                var topBarState by rememberSaveable { (mutableStateOf(false)) }
                var bottomBarState by rememberSaveable { (mutableStateOf(false)) }
                var backButtonState by rememberSaveable { (mutableStateOf(false)) }
                var searchButtonState by rememberSaveable { (mutableStateOf(false)) }

                // Top Bar, Bottom Bar and Back Button Control
                when (currentDestination?.route) {
                    "welcomeScreen" -> {
                        topBarState = false
                        bottomBarState = false
                        backButtonState = false
                    }
                    "booksScreen", "exploreScreen" -> {
                        topBarState = true
                        bottomBarState = true
                        backButtonState = false
                    }
                    "settingsScreen" -> {
                        topBarState = false
                        bottomBarState = true
                        backButtonState = false
                    }
                    "bookDetailScreen/{bookTitle}/{bookAuthor}", "CharactersScreen",
                    "CharacterDetailScreen", "authorScreen", "termsAndConditionsScreen" -> {
                        topBarState = true
                        bottomBarState = false
                        backButtonState = true
                    }
                }

                // Search Button and Floating Action Button Control
                when (currentDestination?.route) {
                    "booksScreen", "CharactersScreen" -> {
                        searchButtonState = true
                        floatingActionButtonState = true
                    }
                    "exploreScreen" -> {
                        searchButtonState = true
                        floatingActionButtonState = false
                    }
                    else -> {
                        searchButtonState = false
                        floatingActionButtonState = false
                    }
                }

                LibrarioApp(
                    navController = navController,
                    currentDestination = currentDestination,
                    topBarState = topBarState,
                    bottomBarState = bottomBarState,
                    backButtonState = backButtonState,
                    floatingActionButtonState = floatingActionButtonState,
                    searchButtonState = searchButtonState,
                    booksScreenViewModel = booksScreenViewModel
                )
            }
        }
    }
}