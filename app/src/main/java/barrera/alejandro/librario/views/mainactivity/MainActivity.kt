package barrera.alejandro.librario.views.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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

                val floatingActionButtonState = rememberSaveable { (mutableStateOf(false)) }
                val topBarState = rememberSaveable { (mutableStateOf(false)) }
                val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
                val backButtonState = rememberSaveable { (mutableStateOf(false)) }

                // Floating Action Button Control
                when (currentDestination?.route) {
                    "booksScreen", "CharactersScreen" -> floatingActionButtonState.value = true
                    else -> floatingActionButtonState.value = false
                }

                // Top Bar, Bottom Bar and Control Back Control
                when (currentDestination?.route) {
                    "welcomeScreen" -> {
                        topBarState.value = false
                        bottomBarState.value = false
                        backButtonState.value = false
                    }
                    "booksScreen", "exploreScreen" -> {
                        topBarState.value = true
                        bottomBarState.value = true
                        backButtonState.value = false
                    }
                    "settingsScreen" -> {
                        topBarState.value = false
                        bottomBarState.value = true
                        backButtonState.value = false
                    }
                    "bookDetailScreen", "CharactersScreen", "CharacterDetailScreen",
                    "authorScreen", "termsAndConditionsScreen" -> {
                        topBarState.value = true
                        bottomBarState.value = false
                        backButtonState.value = true
                    }
                }

                LibrarioApp(
                    navController = navController,
                    currentDestination = currentDestination,
                    topBarState = topBarState,
                    bottomBarState = bottomBarState,
                    backButtonState = backButtonState,
                    floatingActionButtonState = floatingActionButtonState
                )
            }
        }
    }
}