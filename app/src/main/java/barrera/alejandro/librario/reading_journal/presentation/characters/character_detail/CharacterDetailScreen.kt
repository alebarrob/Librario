package barrera.alejandro.librario.reading_journal.presentation.characters.character_detail

import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import barrera.alejandro.librario.reading_journal.presentation.components.DetailedCharacterCard
import barrera.alejandro.librario.reading_journal.presentation.components.OptionButton

@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onNavigateUp: () -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.state

    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
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

    if (showDeleteConfirmationDialog) {
        DeleteConfirmationDialog(
            title = stringResource(id = R.string.delete_character_confirmation_dialog_title),
            text = stringResource(id = R.string.delete_character_confirmation_dialog_text),
            confirmButtonText = stringResource(id = R.string.delete_character_confirm_button_text),
            onConfirmClick = { viewModel.onEvent(CharacterDetailEvent.OnDeleteCharacterConfirmation) },
            dismissButtonText = stringResource(id = R.string.delete_character_dismiss_button_text),
            onDismissRequest = { showDeleteConfirmationDialog = false }
        )
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
        DetailedCharacterCard(
            characterInfo = Triple(state.name, state.description, state.portraitTag),
            onNameChange = { viewModel.onEvent(CharacterDetailEvent.OnNameChange(it)) },
            onDescriptionChange = { viewModel.onEvent(CharacterDetailEvent.OnDescriptionChange(it)) },
            onPortraitTagChange = { viewModel.onEvent(CharacterDetailEvent.OnPortraitTagChange(it)) },
        )
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = spacing.spaceExtraSmall,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            OptionButton(onClick = { viewModel.onEvent(CharacterDetailEvent.OnSaveChangesClick) }) {
                Text(
                    text = stringResource(id = R.string.save_character_changes_button_text),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            OptionButton(
                modifier = Modifier.padding(end = spacing.spaceExtraLarge),
                onClick = { showDeleteConfirmationDialog = true }
            ) {
                Text(
                    text = stringResource(id = R.string.delete_character_button_text),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}