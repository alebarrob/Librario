package barrera.alejandro.librario.explore.presentation.explore_books

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.librario.core.presentation.components.AdaptableColumn
import barrera.alejandro.librario.core.presentation.components.SearchTextField
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExploreBooksScreen(
    modifier: Modifier = Modifier,
    viewModel: ExploreBooksViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.state

    SearchTextField(
        text = state.query,
        onValueChange = {
            viewModel.onEvent(ExploreBooksEvent.OnQueryChange(it))
        },
        onSearch = {
            keyboardController?.hide()
            viewModel.onEvent(ExploreBooksEvent.OnSearch)
        },
        onFocusChanged = {
            viewModel.onEvent(ExploreBooksEvent.OnSearchFocusChange(it.isFocused))
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
                /*BookOverviewCard(
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
                )*/
            }
        }
    }
}