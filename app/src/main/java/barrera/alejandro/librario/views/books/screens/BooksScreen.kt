package barrera.alejandro.librario.views.books.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import barrera.alejandro.librario.viewmodels.books.BooksScreenViewModel
import barrera.alejandro.librario.views.theme.LightRed

@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    landscapeOrientation: Boolean,
    paddingValues: PaddingValues,
    navController: NavController,
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
                    bottom = paddingValues.calculateBottomPadding() + 20.dp
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
                onClick = {
                    navController.navigate(
                        route = "bookDetailScreen/${book.title}/${book.author}/${book.description}"
                    )
                }
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
            .width(140.dp)
            .height(240.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = LightRed),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.primary
        )
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier.padding(
                    vertical = 8.dp,
                    horizontal = 10.dp
                ),
                colors = CardDefaults.cardColors(containerColor = colorScheme.secondary),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            vertical = 5.dp,
                            horizontal = 10.dp
                        )
                        .heightIn(
                            min = 0.dp,
                            max = 150.dp
                        )
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = bookTitle,
                        style = typography.headlineMedium,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 30.sp
                    )
                    Text(
                        text = bookAuthor,
                        style = typography.headlineMedium,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

