package barrera.alejandro.librario.views.books

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barrera.alejandro.librario.R
import barrera.alejandro.librario.models.books.entities.Book
import barrera.alejandro.librario.models.books.entities.BookOptionButtonData
import barrera.alejandro.librario.models.routes.ScreenNavigation
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
    onClickOption: (screen: ScreenNavigation?) -> Unit
) {
    val bookDetailScreenViewModel = hiltViewModel<BookDetailScreenViewModel>()

    val bookTitle by bookDetailScreenViewModel.bookTitle.collectAsState(initial = "Título")
    val bookAuthor by bookDetailScreenViewModel.bookAuthor.collectAsState(initial = "Autor")
    val bookDescription by bookDetailScreenViewModel.bookDescription.collectAsState(initial = "Descripción")
    var booksLoaded by remember { mutableStateOf(false) }

    val bookOptionButtonsData = listOf(
        BookOptionButtonData(
            buttonTextId = R.string.save_changes_button_text,
            destinationScreen = ScreenNavigation.AuthorScreen
        ),
        BookOptionButtonData(
            buttonTextId = R.string.notes_button_text,
            destinationScreen = ScreenNavigation.AuthorScreen
        ),
        BookOptionButtonData(
            buttonTextId = R.string.characters_button_text,
            destinationScreen = ScreenNavigation.AuthorScreen
        ),
        BookOptionButtonData(
            buttonTextId = R.string.change_color_button_text,
            destinationScreen = ScreenNavigation.AuthorScreen
        ),
        BookOptionButtonData(
            buttonTextId = R.string.delete_button_text,
            destinationScreen = ScreenNavigation.AuthorScreen
        ),
    )

    if (bookOptionsState && !booksLoaded) {
        bookDetailScreenViewModel.loadBookInfo()
        booksLoaded = true
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
            onBookDescriptionChange = { bookDetailScreenViewModel.onBookDescriptionChange(it) }
        )
        if (bookOptionsState) {
            BookOptions(
                bookOptionButtonsData = bookOptionButtonsData,
                onClickOption = onClickOption,
            )
        } else {
            BookOptionButton(
                buttonTextId = R.string.accept_button_text,
                onClickOption = {
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
fun BookOptions(
    modifier: Modifier = Modifier,
    bookOptionButtonsData: List<BookOptionButtonData>,
    onClickOption: (screen: ScreenNavigation?) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState()),
        //contentPadding = PaddingValues(all = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 1.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        bookOptionButtonsData.forEach { data ->
            BookOptionButton(
                buttonTextId = data.buttonTextId,
                onClickOption = onClickOption,
                destinationScreen = data.destinationScreen
            )
        }
        /*items(bookOptionButtonsData) { data ->
            BookOptionButton(
                buttonTextId = data.buttonTextId,
                onClickOption = onClickOption,
                destinationScreen = data.destinationScreen
            )
        }*/
    }
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
