package barrera.alejandro.librario.views.commonui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun LibrarioAlertDialog(
    onDismissRequest: () -> Unit,
    title: String,
    text: String,
    onClickConfirmButton: () -> Unit,
    confirmButtonText: String,
    onClickDismissButton: () -> Unit,
    dismissButtonText: String,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = text)
        },
        confirmButton = {
            Button(
                onClick = onClickConfirmButton
            ) {
                Text(
                    text = confirmButtonText,
                    fontSize = 28.sp,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onClickDismissButton
            ) {
                Text(
                    text = dismissButtonText,
                    fontSize = 28.sp,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    )
}