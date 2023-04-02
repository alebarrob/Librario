package barrera.alejandro.librario.reading_journal.presentation.characters.characters_overview

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.components.AdaptableColumn
import barrera.alejandro.librario.core.presentation.components.SearchTextField
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersOverviewViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onNavigateToCharacterDetail: (
        characterId: Int,
        name: String,
        description: String,
        portrait: String
    ) -> Unit
) {
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.onEvent(CharactersOverviewEvent.LoadCharacters)
    }

    Column(
        modifier = modifier.padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Top
    ) {
        SearchTextField(
            Modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall),
            text = state.query,
            onValueChange = {
                viewModel.onEvent(CharactersOverviewEvent.OnQueryChange(it))
            },
            onSearch = {
                keyboardController?.hide()
                viewModel.onEvent(CharactersOverviewEvent.OnSearch)
            }
        )

        AdaptableColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.characters.isEmpty()) {
                Text(
                    modifier = Modifier.padding(horizontal = spacing.spaceMedium),
                    text = stringResource(id = R.string.no_characters_to_show),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium
                )
            } else {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)) {
                    items(state.characters) { character ->
                        CharacterCard(
                            name = character.name,
                            portraitPainter = painterResource(
                                id = viewModel.getPortraitPainterId(character.portraitTag)
                            ),
                            onClick = {
                                onNavigateToCharacterDetail(
                                    character.id,
                                    character.name,
                                    character.description,
                                    character.portraitTag
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    name: String,
    portraitPainter: Painter
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .height(240.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = colorScheme.secondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.primary
        )
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CharacterLabel(
                name = name,
                portraitPainter = portraitPainter
            )
        }
    }
}

@Composable
fun CharacterLabel(
    modifier: Modifier = Modifier,
    name: String,
    portraitPainter: Painter
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier.padding(
            vertical = spacing.spaceSmall,
            horizontal = spacing.spaceMedium
        ),
        colors = CardDefaults.cardColors(containerColor = colorScheme.tertiary),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.spaceSmall)
                .heightIn(
                    min = 0.dp,
                    max = 150.dp
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = portraitPainter,
                contentDescription = stringResource(id = R.string.character_image_description)
            )
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}