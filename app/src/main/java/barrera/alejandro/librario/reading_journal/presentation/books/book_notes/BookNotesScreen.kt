package barrera.alejandro.librario.reading_journal.presentation.books.book_notes

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.components.AdaptableColumn
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing
import barrera.alejandro.librario.core.util.UiEvent
import barrera.alejandro.librario.reading_journal.presentation.components.DeleteConfirmationDialog
import barrera.alejandro.librario.reading_journal.presentation.components.OptionButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookNotesScreen(
    modifier: Modifier = Modifier,
    viewModel: BookNotesViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onNavigateUp: () -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.state

    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

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

    LaunchedEffect(Unit) {
        viewModel.onEvent(BookNotesEvent.LoadNotes)
    }

    if (showDeleteConfirmationDialog) {
        DeleteConfirmationDialog(
            title = stringResource(id = R.string.save_notes_dialog_title),
            text = stringResource(id = R.string.save_notes_dialog_text),
            confirmButtonText = stringResource(id = R.string.save_notes_confirm_back_button_text),
            onConfirmClick = { onNavigateUp() },
            dismissButtonText = stringResource(id = R.string.save_notes_continue_button_text),
            onDismissRequest = { showDeleteConfirmationDialog = false }
        )
    }

    AdaptableColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = spacing.spaceMedium,
            alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBarPadding = paddingValues.calculateTopPadding()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.notes,
            onValueChange = { viewModel.onEvent(BookNotesEvent.OnNotesChange(it)) },
            minLines = 13,
            maxLines = 13,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = colorScheme.onPrimary,
                unfocusedIndicatorColor = colorScheme.onPrimary
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OptionButton(onClick = { showDeleteConfirmationDialog = true }) {
                Text(
                    text = stringResource(id = R.string.cancel_notes_button_text),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            OptionButton(onClick = { viewModel.onEvent(BookNotesEvent.OnSaveNotesClick) }) {
                Text(
                    text = stringResource(id = R.string.save_notes_button_text),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}