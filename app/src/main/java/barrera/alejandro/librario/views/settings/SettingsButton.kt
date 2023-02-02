package barrera.alejandro.librario.views.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.models.routes.ScreenNavigation

@Composable
fun SettingsButton(
    modifier: Modifier = Modifier,
    onClickOption: (screen: ScreenNavigation) -> Unit,
    destinationScreen: ScreenNavigation,
    text: String,
    trailingIconPainter: Painter,
    trailingIconContentDescription: String
) {
    Button(
        onClick = { onClickOption(destinationScreen) },
        shape = ShapeDefaults.Medium,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.secondary
        )
    ) {
        Row(
            modifier = modifier.padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(weight = 1f),
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
            Icon(
                modifier = Modifier.weight(weight = 1f),
                painter = trailingIconPainter,
                contentDescription = trailingIconContentDescription
            )
        }
    }
}