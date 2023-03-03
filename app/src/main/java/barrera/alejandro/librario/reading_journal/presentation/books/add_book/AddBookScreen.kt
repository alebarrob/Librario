package barrera.alejandro.librario.reading_journal.presentation.books.add_book

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.components.AdaptableColumn
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing
import barrera.alejandro.librario.core.util.UiEvent
import barrera.alejandro.librario.core.presentation.components.DetailedBookCard
import barrera.alejandro.librario.reading_journal.presentation.components.OptionButton

@Composable
fun AddBookScreen(
    modifier: Modifier = Modifier,
    viewModel: AddBookViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onNavigateUp: () -> Unit
) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UiEvent.NavigateUp -> onNavigateUp()
            }
        }
    }

    AdaptableColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = spacing.spaceMedium,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBarPadding = paddingValues.calculateTopPadding()
    ) {
        DetailedBookCard(
            bookInfo = Triple(state.title, state.author, state.description),
            onTitleChange = { viewModel.onEvent(AddBookEvent.OnTitleChange(it)) },
            onAuthorChange = { viewModel.onEvent(AddBookEvent.OnAuthorChange(it)) },
            onDescriptionChange = { viewModel.onEvent(AddBookEvent.OnDescriptionChange(it)) }
        )
        OptionButton(onClick = { viewModel.onEvent(AddBookEvent.OnAddBookClick) }) {
            Text(
                text = stringResource(id = R.string.add_book_button_text),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}