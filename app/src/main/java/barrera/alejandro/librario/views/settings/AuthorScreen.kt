package barrera.alejandro.librario.views.settings

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
            .padding(all = 15.dp)
            .padding(top = paddingValues.calculateTopPadding())
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = aboutTheAuthor)
    }
}