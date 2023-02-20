package barrera.alejandro.librario.views.books.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.theme.*

@Composable
fun DetailedCharacterCard(
    modifier: Modifier = Modifier,
    characterName: String,
    onCharacterNameChange: (String) -> Unit,
    characterDescription: String,
    onCharacterDescriptionChange: (String) -> Unit,
    characterPortrait: String,
    onCharacterPortraitChange: (String) -> Unit,
    characterColor: String?
) {
    val characterStyle = when (characterColor) {
        "red" -> Pair(LightRed, DarkRed)
        "blue" -> Pair(LightBlue, DarkBlue)
        else -> Pair(LightGreen, DarkGreen)
    }
    val radioOptions = listOf(
        "Mujer", "Hombre", "No binario"
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Card(
        colors = CardDefaults.cardColors(containerColor = characterStyle.first),
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
            CharacterPortraitPicker(
                characterPortrait = characterPortrait,
                radioOptions = radioOptions,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected,
                onCharacterPortraitChange = onCharacterPortraitChange
            )
            InfoTextField(
                value = characterName,
                onValueChange = onCharacterNameChange,
                label = { Text(text = stringResource(id = R.string.character_info_name)) },
                maxLines = 2,
                height = 70.dp,
                focusedIndicatorColor = characterStyle.second
            )
            InfoTextField(
                value = characterDescription,
                onValueChange = onCharacterDescriptionChange,
                label = { Text(text = stringResource(id = R.string.book_info_description)) },
                maxLines = 4,
                height = 100.dp,
                focusedIndicatorColor = characterStyle.second
            )
        }
    }
}

@Composable
fun CharacterPortraitPicker(
    characterPortrait: String,
    radioOptions: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    onCharacterPortraitChange: (String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorScheme.onPrimary),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(
            topStart = 5.dp,
            topEnd = 5.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(
                    id = when (characterPortrait) {
                        "Mujer" -> R.drawable.ic_woman_icon
                        "Hombre" -> R.drawable.ic_man_icon
                        else -> R.drawable.ic_non_binary_icon
                    }
                ),
                contentDescription = stringResource(id = R.string.character_image_description)
            )
            Column(
                Modifier
                    .selectableGroup()
                    .weight(1f)
            ) {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(36.dp)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    onOptionSelected(text)
                                    onCharacterPortraitChange(text)
                                },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = if (text == "No binario") 13.sp else 16.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}