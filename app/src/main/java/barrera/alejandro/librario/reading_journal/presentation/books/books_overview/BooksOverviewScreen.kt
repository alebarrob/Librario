package barrera.alejandro.librario.reading_journal.presentation.books.books_overview

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.librario.core.presentation.components.AdaptableColumn
import barrera.alejandro.librario.core.presentation.components.SearchTextField
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BooksOverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: BooksOverviewViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onNavigateToBookDetail: (
        bookId: Int,
        title: String,
        author: String,
        description: String
    ) -> Unit
) {
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.onEvent(BooksOverviewEvent.LoadBooks)
    }

    SearchTextField(
        text = state.query,
        onValueChange = {
            viewModel.onEvent(BooksOverviewEvent.OnQueryChange(it))
        },
        onSearch = {
            keyboardController?.hide()
            viewModel.onEvent(BooksOverviewEvent.OnSearch)
        },
        onFocusChanged = {
            viewModel.onEvent(BooksOverviewEvent.OnSearchFocusChange(it.isFocused))
        },
        shouldShowHint = state.isHintVisible
    )

    AdaptableColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        bottomBarPadding = paddingValues.calculateBottomPadding()
    ) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)) {
            items(state.bookEntities) { book ->
                BookOverviewCard(
                    title = book.title,
                    author = book.author,
                    onClick = {
                        onNavigateToBookDetail(
                            book.id,
                            book.title,
                            book.author,
                            book.description
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun BookOverviewCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    author: String
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .height(240.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = colorScheme.secondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BookOverviewLabel(
                title = title,
                author = author
            )
        }
    }
}

@Composable
fun BookOverviewLabel(
    modifier: Modifier = Modifier,
    title: String,
    author: String
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier.padding(spacing.spaceSmall),
        colors = CardDefaults.cardColors(containerColor = colorScheme.tertiary),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.spaceSmall)
                .heightIn(
                    min = spacing.default,
                    max = 150.dp
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = typography.headlineMedium
            )
            Text(
                text = author,
                style = typography.headlineSmall
            )
        }
    }
}

