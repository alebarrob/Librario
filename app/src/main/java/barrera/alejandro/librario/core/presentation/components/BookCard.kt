package barrera.alejandro.librario.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    author: String
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .height(240.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BookLabel(
                title = title,
                author = author
            )
        }
    }
}

@Composable
fun BookLabel(
    modifier: Modifier = Modifier,
    title: String,
    author: String
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier.padding(spacing.spaceSmall),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.spaceSmall)
                .heightIn(
                    min = spacing.default,
                    max = 150.dp
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = author,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}