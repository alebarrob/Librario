package barrera.alejandro.librario.views.welcome

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.R

@Suppress("UNUSED_VALUE")
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navigation: () -> Unit
) {
    var hasNavigated by remember { mutableStateOf(false) }

    if (!hasNavigated) {
        navigateAfterThreeSeconds(navigation)
        hasNavigated = true
    }

    Column(
        modifier
            .background(colorScheme.primary)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(bottom = 5.dp),
            painter = painterResource(R.drawable.ic_welcome_screen),
            contentDescription = stringResource(id = R.string.welcome_screen_image_description)
        )
        Text(
            text = stringResource(id = R.string.welcome_screen_title),
            color = colorScheme.onPrimary,
            style = typography.headlineLarge
        )
        Text(
            text = stringResource(id = R.string.welcome_screen_subtitle),
            color = colorScheme.onPrimary,
            style = typography.headlineMedium
        )
    }
}

fun navigateAfterThreeSeconds(navigation: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ navigation() }, 3000)
}