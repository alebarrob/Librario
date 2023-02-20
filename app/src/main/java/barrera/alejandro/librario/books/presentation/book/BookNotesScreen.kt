package barrera.alejandro.librario.views.books.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barrera.alejandro.librario.R
import barrera.alejandro.librario.books.presentation.book.BookNotesScreenViewModel
import barrera.alejandro.librario.views.books.composables.BookOptionButton
import barrera.alejandro.librario.core.presentation.components.AlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookNotesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    paddingValues: PaddingValues
) {
    val bookNotesScreenViewModel = hiltViewModel<BookNotesScreenViewModel>()
    val bookNotes by bookNotesScreenViewModel.bookNotes.collectAsState(initial = "Aquí puedes escribir tus notas sobre este libro.")
    val bookId by bookNotesScreenViewModel.bookId.collectAsState(initial = 0)

    var openDialog by rememberSaveable { mutableStateOf(false) }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                openDialog = false
            },
            title = "¡Atención!",
            text = "Estás a punto de volver a la pantalla anterior sin guardar tus notas. ¿Estás seguro?",
            onClickConfirmButton = {
                openDialog = false
                navController.popBackStack()
            },
            confirmButtonText = "Volver",
            onClickDismissButton = {
                openDialog = false
            },
            dismissButtonText = "Seguir editando",
        )
    }

    Column(
        modifier = modifier
            .padding(horizontal = 25.dp)
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        TextField(
            modifier = Modifier.fillMaxSize(),
            value = bookNotes,
            onValueChange = { bookNotesScreenViewModel.onBookNotesChange(it) },
            minLines = 15,
            maxLines = 15,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = colorScheme.onPrimary,
                unfocusedIndicatorColor = colorScheme.onPrimary
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BookOptionButton(
                buttonTextId = R.string.save_notes_button_text,
                onClick = {
                    if (bookNotes == "") {
                        navController.popBackStack()
                        bookNotesScreenViewModel.updateNotes(
                            bookNotes = "Aquí puedes escribir tus notas sobre este libro.",
                            bookId = bookId
                        )
                    } else {
                        navController.popBackStack()
                        bookNotesScreenViewModel.updateNotes(bookNotes, bookId)
                    }
                }
            )
            BookOptionButton(
                buttonTextId = R.string.cancel_notes_button_text,
                onClick = {
                    openDialog = true
                }
            )
        }
    }
}