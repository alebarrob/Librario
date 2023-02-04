package barrera.alejandro.librario.views.settings.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.R

@Composable
fun AuthorScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    context: Context
) {
    val aboutTheAuthorInputStream = context.assets.open("text/about_the_author.txt")
    val aboutTheAuthor = aboutTheAuthorInputStream.bufferedReader().use { it.readText() }

    Column(
        modifier
            .padding(
                vertical = 20.dp,
                horizontal = 28.dp
            )
            .padding(top = paddingValues.calculateTopPadding())
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = aboutTheAuthor)
        Text(
            modifier = Modifier.clickable(onClick = {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse("https://www.elcodigoylapluma.com")
                context.startActivity(openURL)
            }),
            textAlign = TextAlign.Start,
            color = colorScheme.primary,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            text = stringResource(id = R.string.website_url)
        )
    }
}