package barrera.alejandro.librario.views.books.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)?,
    maxLines: Int,
    height: Dp,
    focusedIndicatorColor: Color
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        value = value,
        onValueChange = onValueChange,
        label = label,
        maxLines = maxLines,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}