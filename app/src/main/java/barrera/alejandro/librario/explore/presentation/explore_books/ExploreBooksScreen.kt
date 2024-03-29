package barrera.alejandro.librario.explore.presentation.explore_books

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.components.AdaptableColumn
import barrera.alejandro.librario.core.presentation.components.BookCard
import barrera.alejandro.librario.core.presentation.components.SearchTextField
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing
import barrera.alejandro.librario.core.util.UiEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExploreBooksScreen(
    modifier: Modifier = Modifier,
    viewModel: ExploreBooksViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onNavigateToExploreBookDetail: (
        title: String,
        author: String,
        description: String
    ) -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
    }

    Column(
        modifier = modifier.padding(bottom = paddingValues.calculateBottomPadding()),
        verticalArrangement = Arrangement.Top
    ) {
        SearchTextField(
            Modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall),
            text = state.query,
            onValueChange = {
                viewModel.onEvent(ExploreBooksEvent.OnQueryChange(it))
            },
            onSearch = {
                keyboardController?.hide()
                viewModel.onEvent(ExploreBooksEvent.OnSearch)
            }
        )

        AdaptableColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (state.books.isEmpty()) {
                Text(
                    modifier = Modifier.padding(horizontal = spacing.spaceMedium),
                    text = stringResource(id = R.string.explore_info),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium
                )
            } else {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)) {
                    items(state.books) { book ->
                        BookCard(
                            title = book.title,
                            author = book.author,
                            onClick = {
                                onNavigateToExploreBookDetail(
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
}