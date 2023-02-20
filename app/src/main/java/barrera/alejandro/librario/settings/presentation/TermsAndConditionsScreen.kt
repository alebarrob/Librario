package barrera.alejandro.librario.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing

@Composable
fun TermsAndConditionsScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val text = context.assets.open(
        "text/terms_and_conditions.txt"
    ).bufferedReader().use { it.readText() }

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = paddingValues.calculateTopPadding())
            .padding(spacing.spaceLarge),
        verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
    }
}