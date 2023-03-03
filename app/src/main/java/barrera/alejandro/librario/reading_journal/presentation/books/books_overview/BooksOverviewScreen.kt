package barrera.alejandro.librario.reading_journal.presentation.books.books_overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.components.AdaptableColumn
import barrera.alejandro.librario.core.presentation.components.BookCard
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
        if (state.books.isEmpty()) {
            Text(
                modifier = Modifier.padding(horizontal = spacing.spaceMedium),
                text = stringResource(id = R.string.no_books_to_show),
                textAlign = TextAlign.Center,
                style = typography.displayMedium
            )
        } else {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)) {
                items(state.books) { book ->
                    BookCard(
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
}

