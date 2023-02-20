package barrera.alejandro.librario.views.books.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.theme.*

@Composable
fun DetailedBookCard(
    modifier: Modifier = Modifier,
    bookTitle: String,
    onBookTitleChange: (String) -> Unit,
    bookAuthor: String,
    onBookAuthorChange: (String) -> Unit,
    bookDescription: String,
    onBookDescriptionChange: (String) -> Unit,
    bookColor: String?,
    bookOptionButton: @Composable (() -> Unit)? = null
) {
    val bookStyle = when (bookColor) {
        "red" -> Triple(LightRed, DarkRed, R.drawable.ic_red_book)
        "blue" -> Triple(LightBlue, DarkBlue, R.drawable.ic_blue_book)
        else -> Triple(LightGreen, DarkGreen, R.drawable.ic_green_book)
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = bookStyle.first),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.primary
        )
    ) {
        Column(
            modifier = modifier.padding(all = 20.dp),
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
                    painter = painterResource(id = bookStyle.third),
                    contentDescription = stringResource(id = R.string.book_image_description)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = 5.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    InfoTextField(
                        value = bookTitle,
                        onValueChange = onBookTitleChange,
                        label = { Text(text = stringResource(id = R.string.book_info_title)) },
                        maxLines = 2,
                        height = 70.dp,
                        focusedIndicatorColor = bookStyle.second
                    )
                    InfoTextField(
                        value = bookAuthor,
                        onValueChange = onBookAuthorChange,
                        label = { Text(text = stringResource(id = R.string.book_info_author)) },
                        maxLines = 2,
                        height = 70.dp,
                        focusedIndicatorColor = bookStyle.second
                    )
                }
            }
            InfoTextField(
                value = bookDescription,
                onValueChange = onBookDescriptionChange,
                label = { Text(text = stringResource(id = R.string.book_info_description)) },
                maxLines = 4,
                height = 100.dp,
                focusedIndicatorColor = bookStyle.second
            )
            bookOptionButton?.invoke()
        }
    }
}