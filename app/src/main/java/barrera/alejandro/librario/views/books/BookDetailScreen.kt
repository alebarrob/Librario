package barrera.alejandro.librario.views.books

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import barrera.alejandro.librario.R
import barrera.alejandro.librario.models.books.entities.Book
import barrera.alejandro.librario.viewmodels.books.BookDetailScreenViewModel
import barrera.alejandro.librario.views.commonui.BookCard

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
    configuration: Configuration,
    context: Context,
    paddingValues: PaddingValues,
    navController: NavController,
    bookOptionsState: Boolean,
    bookDetailScreenViewModel: BookDetailScreenViewModel
) {
    var bookTitle by rememberSaveable { mutableStateOf("") }
    var bookAuthor by rememberSaveable { mutableStateOf("") }
    var bookDescription by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> modifier
                .padding(all = 20.dp)
                .padding(top = paddingValues.calculateTopPadding())
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
            else -> modifier
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
        BookCard(
            bookTitle = bookTitle,
            onBookTitleChange = { newBookTitle ->
                bookTitle = newBookTitle
            },
            bookAuthor = bookAuthor,
            onBookAuthorChange = { newBookAuthor ->
                bookAuthor = newBookAuthor
            },
            bookDescription = bookDescription,
            onBookDescriptionChange = { newBookDescription ->
                bookDescription = newBookDescription
            },
            readOnly = false
        )
        if (bookOptionsState) {
            BookOptions()
        } else {
            AcceptButton(
                onClick = {
                    bookDetailScreenViewModel.insertBook(
                        Book(
                            id = 0,
                            title = bookTitle,
                            author = bookAuthor,
                            description = bookDescription
                        )
                    )
                    navController.popBackStack()
                    Toast.makeText(
                        context, "El libro fue añadido correctamente.", Toast.LENGTH_LONG
                    ).show()
                }
            )
        }
    }
}

@Composable
fun AcceptButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 30.dp),
        onClick = onClick,
        contentPadding = PaddingValues(vertical = 10.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.accept_button_text),
            fontSize = 28.sp,
            style = typography.labelMedium
        )
    }
}

@Composable
fun BookOptions() {
    Text(text = "Test")
}
