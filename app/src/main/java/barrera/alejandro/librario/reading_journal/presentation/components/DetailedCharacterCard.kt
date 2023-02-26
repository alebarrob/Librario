package barrera.alejandro.librario.reading_journal.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing

@Composable
fun DetailedCharacterCard(
    modifier: Modifier = Modifier,
    characterInfo: Triple<String, String, String>,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onPortraitTagChange: (String) -> Unit
) {
    val radioTextOptions = listOf("Mujer", "Hombre", "No binario")
    val portraitOptions = listOf(
        painterResource(id = R.drawable.ic_woman_icon),
        painterResource(id = R.drawable.ic_man_icon),
        painterResource(id = R.drawable.ic_non_binary_icon),
    )
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = colorScheme.secondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.padding(all = spacing.spaceMedium),
            verticalArrangement = Arrangement.spacedBy(
                space = spacing.spaceExtraSmall,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PortraitPicker(
                portraitTag = characterInfo.third,
                onPortraitTagChange = onPortraitTagChange,
                radioOptions = radioTextOptions,
                portraitOptions = portraitOptions
            )
            InfoTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                value = characterInfo.first,
                onValueChange = onNameChange,
                label = { Text(text = stringResource(id = R.string.character_info_name)) }
            )
            InfoTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                value = characterInfo.second,
                onValueChange = onDescriptionChange,
                label = { Text(text = stringResource(id = R.string.character_info_description)) },
                maxLines = 4
            )
        }
    }
}

@Composable
fun PortraitPicker(
    portraitTag: String,
    onPortraitTagChange: (String) -> Unit,
    radioOptions: List<String>,
    portraitOptions: List<Painter>
) {
    val spacing = LocalSpacing.current

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
                .padding(vertical = spacing.spaceExtraSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.weight(1f),
                painter = portraitOptions[radioOptions.indexOf(portraitTag)],
                contentDescription = stringResource(id = R.string.character_image_description)
            )
            RadioGroup(
                options = radioOptions,
                selectedOption = portraitTag,
                onOptionSelected = { onPortraitTagChange(it) }
            )

        }
    }
}

@Composable
fun RowScope.RadioGroup(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
) {
    val spacing = LocalSpacing.current

    Column(
        modifier
            .selectableGroup()
            .weight(1f)
    ) {
        options.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(spacing.spaceLarge)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
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
                    modifier = Modifier.padding(start = spacing.spaceMedium)
                )
            }
        }
    }
}