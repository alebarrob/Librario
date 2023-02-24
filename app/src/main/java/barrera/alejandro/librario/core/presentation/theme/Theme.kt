package barrera.alejandro.librario.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val ColorScheme = lightColorScheme(
    background = BabyPowder,
    primary = RaisinBlack,
    onPrimary = BabyPowder,
    secondary = MaximumYellowRed,
    onSecondary = RaisinBlack,
    tertiary = GhostWhite,
    onTertiary = RaisinBlack
)

@Composable
fun SalvaIdeasTheme(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = ColorScheme.primary,
            darkIcons = false
        )
    }
    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colorScheme = ColorScheme,
            typography = Typography,
            content = content
        )
    }
}