package barrera.alejandro.librario.books.presentation.book

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barrera.alejandro.librario.R
import barrera.alejandro.librario.books.data.book.entity.Book
import barrera.alejandro.librario.views.books.composables.BookOptionButton
import barrera.alejandro.librario.views.books.composables.DetailedBookCard

@Composable
fun AddBookScreen(
    modifier: Modifier = Modifier,
    landscapeOrientation: Boolean,
    paddingValues: PaddingValues,
    navController: NavController,
    addBookScreenViewModel: AddBookScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var bookTitle by remember { mutableStateOf("") }
    var bookAuthor by remember { mutableStateOf("") }
    var bookDescription by remember { mutableStateOf("") }

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
            onBookTitleChange = { bookTitle = it },
            bookAuthor = bookAuthor,
            onBookAuthorChange = { bookAuthor = it },
            bookDescription = bookDescription,
            onBookDescriptionChange = { bookDescription = it },
            bookColor = "red"
        )
        BookOptionButton(
            buttonTextId = R.string.accept_button_text,
            onClick = {
                if (bookTitle == "" || bookAuthor == "" || bookDescription == "") {
                    Toast.makeText(context, "¡No has escrito la información del libro!", Toast.LENGTH_LONG).show()
                } else {
                    addBookScreenViewModel.insertBook(
                        Book(
                            id = 0,
                            title = bookTitle,
                            author = bookAuthor,
                            description = bookDescription,
                            color = "red",
                            notes = "Aquí puedes escribir tus notas sobre este libro."
                        )
                    )
                    navController.popBackStack()
                    Toast.makeText(context, "El libro fue añadido correctamente.", Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}