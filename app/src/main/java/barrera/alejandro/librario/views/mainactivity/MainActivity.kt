package barrera.alejandro.librario.views.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.librario.models.routes.ScreenNavigation.*
import barrera.alejandro.librario.views.commonui.LibrarioBottomBar
import barrera.alejandro.librario.views.commonui.LibrarioTopBar
import barrera.alejandro.librario.views.screens.*
import barrera.alejandro.librario.views.theme.SalvaIdeasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SalvaIdeasTheme {
                val configuration = LocalConfiguration.current
                val context = LocalContext.current
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val topBarState = rememberSaveable { (mutableStateOf(false)) }
                val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
                val screens = listOf(BooksScreen, ExploreScreen, SettingsScreen)

                // Control TopBar and BottomBar
                when (currentDestination?.route) {
                    "welcomeScreen" -> {
                        topBarState.value = false
                        bottomBarState.value = false
                    }
                    "booksScreen" -> {
                        topBarState.value = false
                        bottomBarState.value = true
                    }
                    "settingsScreen" -> {
                        topBarState.value = false
                        bottomBarState.value = true
                    }
                    "authorScreen" -> {
                        topBarState.value = true
                        bottomBarState.value = false
                    }
                    "termsAndConditionsScreen" -> {
                        topBarState.value = true
                        bottomBarState.value = false
                    }
                }

                Scaffold(
                    topBar = {
                        LibrarioTopBar(
                            navController = navController,
                            topBarState = topBarState
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
                    containerColor = colorScheme.background
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = WelcomeScreen.route
                    ) {
                        composable(route = WelcomeScreen.route) {
                            WelcomeScreen(
                                navigation = {
                                    navController.navigate(BooksScreen.route) {
                                        popUpTo(WelcomeScreen.route) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(route = BooksScreen.route) {
                            BooksScreen(paddingValues = paddingValues)
                        }
                        composable(route = ExploreScreen.route) {
                            ExploreScreen(paddingValues = paddingValues)
                        }
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
        }
    }
}