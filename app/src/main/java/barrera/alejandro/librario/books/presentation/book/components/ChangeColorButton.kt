package barrera.alejandro.librario.views.books.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChangeColorButton(
    modifier: Modifier = Modifier,
    buttonColor: Color,
    onCLick: () -> Unit
) {
    Button(
        modifier = modifier.size(width = 80.dp, height = 60.dp),
        onClick = onCLick,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) { }
}