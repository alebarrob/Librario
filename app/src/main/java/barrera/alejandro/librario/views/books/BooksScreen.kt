package barrera.alejandro.librario.views.books

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.librario.R
import barrera.alejandro.librario.viewmodels.books.BooksScreenViewModel
import barrera.alejandro.librario.views.theme.LightRed

@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    landscapeOrientation: Boolean,
    paddingValues: PaddingValues,
    booksScreenViewModel: BooksScreenViewModel
) {
    val books by booksScreenViewModel.books.collectAsState(initial = listOf())

    LazyRow(
        modifier = if (landscapeOrientation) {
            modifier
                .padding(
                    start = 20.dp,
                    top = paddingValues.calculateTopPadding() + 20.dp,
                    end = 20.dp,
                    bottom = paddingValues.calculateBottomPadding() + 20.dp
                )
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        } else {
            modifier
                .padding(
                    start = 20.dp,
                    top = paddingValues.calculateTopPadding(),
                    end = 20.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .fillMaxSize()
        },
        horizontalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(books) { book ->
            SimpleBookCard(
                bookTitle = book.title,
                bookAuthor = book.author,
                onClick = {  }
            )
        }
    }
}

@Composable
fun SimpleBookCard(
    modifier: Modifier = Modifier,
    bookTitle: String,
    bookAuthor: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(280.dp)
            .height(180.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = LightRed),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.primary
        )
    ) {
        Row(
            modifier = modifier
                .padding(
                    vertical = 20.dp,
                    horizontal = 15.dp
                )
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                modifier = modifier
                    .weight(1f),
                painter = painterResource(id = R.drawable.ic_red_book),
                contentDescription = stringResource(id = R.string.book_image_description)
            )
            Column(
                modifier = modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 3.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                SimpleBookCardText(
                    text = bookTitle,
                    fontSize = 25.sp
                )
                SimpleBookCardText(
                    text = bookAuthor,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun SimpleBookCardText(
    text: String,
    fontSize: TextUnit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorScheme.secondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = typography.headlineMedium,
                fontSize = fontSize,
                textAlign = TextAlign.Center
            )
        }
    }
}

