package barrera.alejandro.librario.welcome.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import barrera.alejandro.librario.R
import kotlinx.coroutines.*

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToBooksOverview: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        onNavigateToBooksOverview()
    }

    Column(
        modifier
            .fillMaxSize()
            .background(colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_welcome_screen),
            contentDescription = stringResource(id = R.string.welcome_screen_image_description)
        )
        Text(
            text = stringResource(id = R.string.welcome_screen_title),
            color = colorScheme.onPrimary,
            style = typography.displayLarge
        )
        Text(
            text = stringResource(id = R.string.welcome_screen_subtitle),
            color = colorScheme.onPrimary,
            style = typography.displayMedium
        )
    }
}