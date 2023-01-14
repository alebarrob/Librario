package barrera.alejandro.librario.views.books

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import barrera.alejandro.librario.models.books.entities.Book
import barrera.alejandro.librario.viewmodels.books.BooksScreenViewModel
import barrera.alejandro.librario.views.commonui.BookCard

@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    booksScreenViewModel: BooksScreenViewModel
) {
    val books by booksScreenViewModel.books.collectAsState(initial = listOf())

    Column(
        modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BooksRow(books = books)
    }
}

@Composable
fun BooksRow(books: List<Book>) {
    LazyRow(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(books) { book ->
            BookCard(
                bookTitle = book.title,
                onBookTitleChange = { },
                bookAuthor = book.author,
                onBookAuthorChange = { },
                bookDescription = book.description,
                onBookDescriptionChange = { },
                readOnly = true
            )
        }
    }
}