package barrera.alejandro.librario.explore.presentation.explore_book_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import barrera.alejandro.librario.core.presentation.components.AdaptableColumn
import barrera.alejandro.librario.core.presentation.components.DetailedBookCard
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing

@Composable
fun ExploreBookDetailScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: ExploreBookDetailViewModel = viewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state

    AdaptableColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = spacing.spaceMedium,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBarPadding = paddingValues.calculateTopPadding()
    ) {
        DetailedBookCard(
            bookInfo = Triple(state.title, state.author, state.description),
            onTitleChange = { },
            onAuthorChange = { },
            onDescriptionChange = { }
        )
    }
}