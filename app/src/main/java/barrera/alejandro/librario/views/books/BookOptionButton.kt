package barrera.alejandro.librario.views.books

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.librario.models.routes.ScreenNavigation

@Composable
fun BookOptionButton(
    modifier: Modifier = Modifier,
    buttonTextId: Int,
    onClickOption: (screen: ScreenNavigation?) -> Unit,
    destinationScreen: ScreenNavigation? = null,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 30.dp),
        onClick = { onClickOption(destinationScreen) },
        contentPadding = PaddingValues(vertical = 10.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
    ) {
        Text(
            text = stringResource(id = buttonTextId),
            fontSize = 28.sp,
            style = MaterialTheme.typography.labelMedium
        )
    }
}