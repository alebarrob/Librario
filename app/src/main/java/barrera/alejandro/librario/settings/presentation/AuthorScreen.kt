package barrera.alejandro.librario.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing
import barrera.alejandro.librario.settings.presentation.components.Hyperlink

@Composable
fun AuthorScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val text = context.assets.open(
        "text/about_the_author.txt"
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
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
        Hyperlink(
            text = stringResource(id = R.string.website_url),
            url = stringResource(id = R.string.website_url),
            context = context,
            textAlign = TextAlign.Center
        )
    }
}