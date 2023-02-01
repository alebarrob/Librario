package barrera.alejandro.librario.views.books

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import barrera.alejandro.librario.R
import barrera.alejandro.librario.models.books.entities.Book
import barrera.alejandro.librario.viewmodels.books.BookDetailScreenViewModel
import barrera.alejandro.librario.views.theme.DarkRed
import barrera.alejandro.librario.views.theme.LightRed

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
    landscapeOrientation: Boolean,
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
            onBookTitleChange = { newBookTitle -> bookTitle = newBookTitle },
            bookAuthor = bookAuthor,
            onBookAuthorChange = { newBookAuthor -> bookAuthor = newBookAuthor },
            bookDescription = bookDescription,
            onBookDescriptionChange = { newBookDescription -> bookDescription = newBookDescription }
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

@Composable
fun DetailedBookCard(
    modifier: Modifier = Modifier,
    bookTitle: String,
    onBookTitleChange: (String) -> Unit,
    bookAuthor: String,
    onBookAuthorChange: (String) -> Unit,
    bookDescription: String,
    onBookDescriptionChange: (String) -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = LightRed),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 5.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_red_book),
                    contentDescription = stringResource(id = R.string.book_image_description)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = 5.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    BookInfoTextField(
                        value = bookTitle,
                        onValueChange = onBookTitleChange,
                        label = { Text(text = stringResource(id = R.string.book_info_title)) },
                        maxLines = 2,
                        height = 70.dp
                    )
                    BookInfoTextField(
                        value = bookAuthor,
                        onValueChange = onBookAuthorChange,
                        label = { Text(text = stringResource(id = R.string.book_info_author)) },
                        maxLines = 2,
                        height = 70.dp
                    )
                }
            }
            BookInfoTextField(
                value = bookDescription,
                onValueChange = onBookDescriptionChange,
                label = { Text(text = stringResource(id = R.string.book_info_description)) },
                maxLines = 4,
                height = 100.dp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookInfoTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)?,
    maxLines: Int,
    height: Dp
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        value = value,
        onValueChange = onValueChange,
        label = label,
        maxLines = maxLines,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = DarkRed,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
