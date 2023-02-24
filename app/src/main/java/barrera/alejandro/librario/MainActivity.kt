package barrera.alejandro.librario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.librario.core.presentation.CoreViewModel
import barrera.alejandro.librario.core.presentation.components.*
import barrera.alejandro.librario.core.presentation.navigation.NavGraph
import barrera.alejandro.librario.core.presentation.navigation.NavigationScreen.*
import barrera.alejandro.librario.core.presentation.theme.SalvaIdeasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SalvaIdeasTheme {
                val viewModel by viewModels<CoreViewModel>()
                val uiState = viewModel.coreState

                val navController = rememberNavController()
                val currentDestination =
                    navController.currentBackStackEntryAsState().value?.destination

                UiController(
                    viewModel = viewModel,
                    currentDestination = currentDestination
                )

                Scaffold(
                    topBar = {
                        TopBar(
                            onBackClick = { navController.navigateUp() },
                            topBarState = uiState.isTopBarVisible
                        )
                    },
                    bottomBar = {
                        BottomBar(
                            onItemClick = { screen ->
                                navController.navigate(screen.route) {
                                    popUpTo(BooksOverviewScreen.route) { inclusive = false }
                                    launchSingleTop = true
                                }
                            },
                            bottomBarState = uiState.isBottomBarVisible,
                            currentDestination = currentDestination
                        )
                    },
                    floatingActionButton = {
                        FloatingButton(
                            onClick = {
                                when (currentDestination?.route) {
                                    "books" -> {
                                        navController.navigate(route = "addBook")
                                    }
                                    "characters" -> {
                                        navController.navigate(route = "addCharacter")
                                    }
                                }
                            },
                            floatingButtonState = uiState.isFloatingButtonVisible,
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.background
                ) { paddingValues ->
                    NavGraph(
                        navController = navController,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}