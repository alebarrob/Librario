package barrera.alejandro.librario.views.books.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookOptionButton(
    modifier: Modifier = Modifier,
    buttonTextId: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.padding(all = 10.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorScheme.secondary,
            contentColor = colorScheme.primary
        ),
        contentPadding = PaddingValues(vertical = 13.dp, horizontal = 20.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.primary
        )
    ) {
        Text(
            text = stringResource(id = buttonTextId),
            fontSize = 28.sp,
            style = MaterialTheme.typography.labelMedium
        )
    }
}