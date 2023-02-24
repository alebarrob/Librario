package barrera.alejandro.librario.reading_journal.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DeleteConfirmationDialog(
    title: String,
    text: String,
    confirmButtonText: String,
    onConfirmClick: () -> Unit,
    dismissButtonText: String,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium
            )
        },
        text = { Text(text = text) },
        confirmButton = {
            Button(onClick = onConfirmClick) {
                Text(
                    text = confirmButtonText,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Text(
                    text = dismissButtonText,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    )
}