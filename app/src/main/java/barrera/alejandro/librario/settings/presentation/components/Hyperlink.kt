package barrera.alejandro.librario.settings.presentation.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun Hyperlink(
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.Underline,
    text: String,
    url: String,
) {
    val context = LocalContext.current
    val openURL = Intent(Intent.ACTION_VIEW)

    Text(
        text = text,
        modifier = modifier.clickable(
            onClick = {
                openURL.data = Uri.parse(url)
                context.startActivity(openURL)
            }
        ),
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        textAlign = textAlign,
        textDecoration = textDecoration
    )
}