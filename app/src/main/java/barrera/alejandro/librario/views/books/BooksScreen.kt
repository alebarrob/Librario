package barrera.alejandro.librario.views.books

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    onClickInsertBook: () -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onClickInsertBook() }
        ) {
            Text(text = "Añadir")
        }
    }
}