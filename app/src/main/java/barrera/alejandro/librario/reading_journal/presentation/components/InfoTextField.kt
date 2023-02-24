package barrera.alejandro.librario.reading_journal.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoTextField(
    modifier: Modifier = Modifier,
    focusedIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = focusedIndicatorColor,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.onPrimary
    ),
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    maxLines: Int = 2
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = label,
        maxLines = maxLines,
        colors = colors
    )
}