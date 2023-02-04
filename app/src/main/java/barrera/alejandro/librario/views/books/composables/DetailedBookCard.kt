package barrera.alejandro.librario.views.books.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.R
import barrera.alejandro.librario.views.theme.DarkRed
import barrera.alejandro.librario.views.theme.LightRed

@Composable
fun DetailedBookCard(
    modifier: Modifier = Modifier,
    bookTitle: String,
    onBookTitleChange: (String) -> Unit,
    bookAuthor: String,
    onBookAuthorChange: (String) -> Unit,
    bookDescription: String,
    onBookDescriptionChange: (String) -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = LightRed),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp),
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
                    painter = painterResource(id = R.drawable.ic_red_book),
                    contentDescription = stringResource(id = R.string.book_image_description)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = 5.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    BookInfoTextField(
                        value = bookTitle,
                        onValueChange = onBookTitleChange,
                        label = { Text(text = stringResource(id = R.string.book_info_title)) },
                        maxLines = 2,
                        height = 70.dp
                    )
                    BookInfoTextField(
                        value = bookAuthor,
                        onValueChange = onBookAuthorChange,
                        label = { Text(text = stringResource(id = R.string.book_info_author)) },
                        maxLines = 2,
                        height = 70.dp
                    )
                }
            }
            BookInfoTextField(
                value = bookDescription,
                onValueChange = onBookDescriptionChange,
                label = { Text(text = stringResource(id = R.string.book_info_description)) },
                maxLines = 4,
                height = 100.dp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookInfoTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)?,
    maxLines: Int,
    height: Dp
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        value = value,
        onValueChange = onValueChange,
        label = label,
        maxLines = maxLines,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = DarkRed,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}