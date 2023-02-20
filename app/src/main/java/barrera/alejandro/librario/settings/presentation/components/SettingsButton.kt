package barrera.alejandro.librario.settings.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing

@Composable
fun SettingsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    trailingIconPainter: Painter
) {
    val spacing = LocalSpacing.current

    Button(
        onClick = onClick,
        shape = ShapeDefaults.Medium,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.secondary
        ),
        contentPadding = PaddingValues(
            vertical = spacing.spaceMedium,
            horizontal = spacing.spaceLarge
        )
    ) {
        Text(
            modifier = modifier.weight(1f),
            text = text,
            textAlign = TextAlign.Center,
            maxLines = 2,
            style = MaterialTheme.typography.labelLarge
        )
        Icon(
            modifier = modifier.weight(1f),
            painter = trailingIconPainter,
            contentDescription = stringResource(id = R.string.settings_icons_description)
        )
    }
}