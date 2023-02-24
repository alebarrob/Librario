package barrera.alejandro.librario.reading_journal.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing

@Composable
fun DetailedBookCard(
    modifier: Modifier = Modifier,
    bookInfo: Triple<String, String, String>,
    onTitleChange: (String) -> Unit,
    onAuthorChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor =  MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.padding(all = spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = spacing.spaceExtraSmall,
                alignment = Alignment.CenterVertically
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_book),
                    contentDescription = stringResource(id = R.string.book_image_description)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = spacing.spaceExtraSmall,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    InfoTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        value = bookInfo.first,
                        onValueChange = { onTitleChange(it) },
                        label = { Text(text = stringResource(id = R.string.book_info_title)) }
                    )
                    InfoTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        value = bookInfo.second,
                        onValueChange = { onAuthorChange(it) },
                        label = { Text(text = stringResource(id = R.string.book_info_author)) }
                    )
                }
            }
            InfoTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                value = bookInfo.third,
                onValueChange = { onDescriptionChange(it) },
                label = { Text(text = stringResource(id = R.string.book_info_description)) },
                maxLines = 4
            )
        }
    }
}