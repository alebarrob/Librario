package barrera.alejandro.salvaideas.views.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.salvaideas.models.routes.ScreenNavigation.BooksScreen
import barrera.alejandro.salvaideas.models.routes.ScreenNavigation.SettingsScreen
import barrera.alejandro.salvaideas.views.commonui.SalvaIdeasBottomBar
import barrera.alejandro.salvaideas.views.screens.BooksScreen
import barrera.alejandro.salvaideas.views.screens.SettingsScreen
import barrera.alejandro.salvaideas.views.theme.SalvaIdeasTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SalvaIdeasTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val configuration = LocalConfiguration.current
                val screens = listOf(BooksScreen, SettingsScreen)

                Scaffold(
                    bottomBar = {
                        SalvaIdeasBottomBar(
                            navController = navController,
                            currentDestination = currentDestination,
                            screens = screens
                        )
                    },
                    containerColor = colorScheme.background
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = BooksScreen.route
                    ) {
                        composable(route = BooksScreen.route) {
                            BooksScreen(paddingValues = paddingValues)
                        }
                        composable(route = SettingsScreen.route) {
                            SettingsScreen(
                                paddingValues = paddingValues,
                                configuration = configuration
                            )
                        }
                    }
                }
            }
        }
    }
}