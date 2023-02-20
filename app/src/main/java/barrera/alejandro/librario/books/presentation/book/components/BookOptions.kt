package barrera.alejandro.librario.views.books.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.books.data.book.entity.BookOptionButtonData

@Composable
fun BookOptions(bookOptionButtonsData: List<BookOptionButtonData>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = 1.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(start = 20.dp, end = 70.dp)
    ) {
        items(bookOptionButtonsData) { data ->
            BookOptionButton(
                buttonTextId = data.buttonTextId,
                onClick = data.onClick
            )
        }
    }
}