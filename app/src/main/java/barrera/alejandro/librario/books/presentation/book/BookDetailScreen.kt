package barrera.alejandro.librario.views.books.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barrera.alejandro.librario.R
import barrera.alejandro.librario.books.data.book.entity.BookOptionButtonData
import barrera.alejandro.librario.books.presentation.book.BookDetailScreenViewModel
import barrera.alejandro.librario.views.books.composables.BookOptions
import barrera.alejandro.librario.views.books.composables.DetailedBookCard
import barrera.alejandro.librario.core.presentation.components.AlertDialog

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
    landscapeOrientation: Boolean,
    paddingValues: PaddingValues,
    navController: NavController,
    bookDetailScreenViewModel: BookDetailScreenViewModel = hiltViewModel(),
    onCharacterBookIdForRoomInsertionChange: (Int) -> Unit
) {
    val context = LocalContext.current

    val bookId by bookDetailScreenViewModel.bookId.collectAsState(initial = 0)
    val bookTitle by bookDetailScreenViewModel.bookTitle.collectAsState(initial = "")
    val bookAuthor by bookDetailScreenViewModel.bookAuthor.collectAsState(initial = "")
    val bookDescription by bookDetailScreenViewModel.bookDescription.collectAsState(initial = "")
    val bookColor by bookDetailScreenViewModel.bookColor.collectAsState(initial = "red")
    val bookNotes by bookDetailScreenViewModel.bookNotes.collectAsState(initial = "Aquí puedes escribir tus notas sobre este libro.")

    var openDialog by rememberSaveable { mutableStateOf(false) }

    val bookOptionButtonsData = listOf(
        BookOptionButtonData(
            buttonTextId = R.string.save_changes_button_text,
            onClick = {
                if (bookTitle == "" || bookAuthor == "" || bookDescription == "") {
                    Toast.makeText(context, "¡No has escrito la información del libro!", Toast.LENGTH_LONG).show()
                } else {
                    bookDetailScreenViewModel.updateBook(bookTitle, bookAuthor, bookDescription, bookId)
                    navController.popBackStack()
                    Toast.makeText(context, "Los cambios se guardaron con éxito.", Toast.LENGTH_LONG).show()
                }
            },
        ),
        BookOptionButtonData(
            buttonTextId = R.string.notes_button_text,
            onClick = {
                navController.navigate(route = "bookNotes/${bookId}/${bookNotes}")
            }
        ),
        BookOptionButtonData(
            buttonTextId = R.string.characters_button_text,
            onClick = {
                onCharacterBookIdForRoomInsertionChange(bookId)
                navController.navigate(route = "characters/${bookId}")
            }
        ),
        BookOptionButtonData(
            buttonTextId = R.string.change_color_button_text,
            onClick = {
                navController.navigate(route = "changeBookColor/${bookId}")
            }
        ),
        BookOptionButtonData(
            buttonTextId = R.string.delete_button_text,
            onClick = {
                openDialog = true
            }
        ),
    )

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                openDialog = false
            },
            title = "¡Atención!",
            text = "Estás a punto de borrar el libro. " +
                    "Esta acción es irreversible y se perderán todas las notas " +
                    "y los personajes guardados. ¿Seguro que quieres continuar?",
            onClickConfirmButton = {
                openDialog = false
                navController.popBackStack()
                bookDetailScreenViewModel.deleteBook(bookId)
                Toast.makeText(context, "El libro fue borrado con éxito.", Toast.LENGTH_LONG).show()
            },
            confirmButtonText = "Continuar",
            onClickDismissButton = {
                openDialog = false
            },
            dismissButtonText = "Cancelar",
        )
    }

    Column(
        modifier = if (landscapeOrientation) {
            modifier
                .padding(all = 20.dp)
                .padding(top = paddingValues.calculateTopPadding())
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        } else {
            modifier
                .padding(all = 20.dp)
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        if (bookColor != null) {
            DetailedBookCard(
                bookTitle = bookTitle,
                onBookTitleChange = { bookDetailScreenViewModel.onBookTitleChange(it) },
                bookAuthor = bookAuthor,
                onBookAuthorChange = { bookDetailScreenViewModel.onBookAuthorChange(it) },
                bookDescription = bookDescription,
                onBookDescriptionChange = { bookDetailScreenViewModel.onBookDescriptionChange(it) },
                bookColor = bookColor
            )
            BookOptions(bookOptionButtonsData = bookOptionButtonsData)
        }
    }
}
