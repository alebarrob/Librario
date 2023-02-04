package barrera.alejandro.librario.views.books.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barrera.alejandro.librario.R
import barrera.alejandro.librario.models.books.entities.BookOptionButtonData
import barrera.alejandro.librario.viewmodels.books.BookDetailScreenViewModel
import barrera.alejandro.librario.views.books.composables.BookOptionButton
import barrera.alejandro.librario.views.books.composables.DetailedBookCard
import barrera.alejandro.librario.views.commonui.LibrarioAlertDialog

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
    landscapeOrientation: Boolean,
    paddingValues: PaddingValues,
    context: Context,
    navController: NavController
) {
    val bookDetailScreenViewModel = hiltViewModel<BookDetailScreenViewModel>()

    val bookId by bookDetailScreenViewModel.bookId.collectAsState(initial = 0)
    val bookTitle by bookDetailScreenViewModel.bookTitle.collectAsState(initial = "")
    val bookAuthor by bookDetailScreenViewModel.bookAuthor.collectAsState(initial = "")
    val bookDescription by bookDetailScreenViewModel.bookDescription.collectAsState(initial = "")
    val bookColor by bookDetailScreenViewModel.bookColor.collectAsState(initial = "red")

    var openDialog by remember { mutableStateOf(false) }

    val bookOptionButtonsData = listOf(
        BookOptionButtonData(
            buttonTextId = R.string.save_changes_button_text,
            onClick = {
                bookDetailScreenViewModel.updateBook(bookTitle, bookAuthor, bookDescription, bookId)
                navController.popBackStack()
                Toast.makeText(context, "Los cambios se guardaron con éxito.", Toast.LENGTH_LONG).show()
            },
        ),
        BookOptionButtonData(
            buttonTextId = R.string.notes_button_text,
            onClick = {  }
        ),
        BookOptionButtonData(
            buttonTextId = R.string.characters_button_text,
            onClick = {  }
        ),
        BookOptionButtonData(
            buttonTextId = R.string.change_color_button_text,
            onClick = {
                navController.navigate(route = "changeBookColorScreen/${bookId}")
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
        LibrarioAlertDialog(
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

@Composable
fun BookOptions(bookOptionButtonsData: List<BookOptionButtonData>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = 1.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(start = 30.dp, end = 83.dp)
    ) {
        items(bookOptionButtonsData) { data ->
            BookOptionButton(
                buttonTextId = data.buttonTextId,
                onClick = data.onClick
            )
        }
    }
}
