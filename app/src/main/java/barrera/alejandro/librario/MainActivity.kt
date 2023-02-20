package barrera.alejandro.librario

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.librario.core.presentation.CoreViewModel
import barrera.alejandro.librario.core.presentation.components.UiController
import barrera.alejandro.librario.core.presentation.components.*
import barrera.alejandro.librario.core.presentation.navigation.NavGraph
import barrera.alejandro.librario.core.presentation.theme.SalvaIdeasTheme
import barrera.alejandro.librario.views.books.screens.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SalvaIdeasTheme {
                val navController = rememberNavController()
                val currentDestination =
                    navController.currentBackStackEntryAsState().value?.destination

                val viewModel by viewModels<CoreViewModel>()
                val uiState = viewModel.uiState

                val landscapeOrientation = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
                var characterBookIdForRoomInsertion by rememberSaveable { mutableStateOf(0) }

                UiController(currentDestination = currentDestination, viewModel = viewModel)

                Scaffold(
                    topBar = {
                        TopBar(
                            navController = navController,
                            topBarState = uiState.isTopBarVisible
                        )
                    },
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            bottomBarState = uiState.isBottomBarVisible,
                            currentDestination = currentDestination
                        )
                    },
                    floatingActionButton = {
                        FloatingButton(
                            onClickAddBookButton = {
                                when (currentDestination?.route) {
                                    "books" -> {
                                        navController.navigate(route = "addBook")
                                    }
                                    "characters/{bookId}" -> {
                                        navController.navigate(route = "addCharacter/${characterBookIdForRoomInsertion}")
                                    }
                                }
                            },
                            addBookButtonState = uiState.isFloatingButtonVisible,
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.background
                ) { paddingValues ->
                    NavGraph(
                        navController = navController,
                        paddingValues = paddingValues,
                        landscapeOrientation = landscapeOrientation,
                        onCharacterBookIdForRoomInsertionChange = {
                            characterBookIdForRoomInsertion = it
                        }
                    )
                }
            }
        }
    }
}