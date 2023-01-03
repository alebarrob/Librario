package barrera.alejandro.librario.views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AuthorScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    Column(
        modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "AuthorScreen")
    }
}