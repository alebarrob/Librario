package barrera.alejandro.librario.views.books.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barrera.alejandro.librario.R
import barrera.alejandro.librario.books.presentation.character.CharactersScreenViewModel
import barrera.alejandro.librario.core.presentation.theme.LightBlue
import barrera.alejandro.librario.core.presentation.theme.LightGreen
import barrera.alejandro.librario.core.presentation.theme.LightRed

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    landscapeOrientation: Boolean,
    paddingValues: PaddingValues,
    navController: NavController
) {
    val charactersScreenViewModel = hiltViewModel<CharactersScreenViewModel>()

    val characters by charactersScreenViewModel.characters.collectAsState(initial = listOf())

    LazyRow(
        modifier = if (landscapeOrientation) {
            modifier
                .padding(
                    start = 20.dp,
                    top = paddingValues.calculateTopPadding() + 20.dp,
                    end = 20.dp
                )
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        } else {
            modifier
                .padding(
                    start = 20.dp,
                    top = paddingValues.calculateTopPadding(),
                    end = 20.dp
                )
                .fillMaxSize()
        },
        horizontalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(characters) { character ->
            SimpleCharacterCard(
                characterName = character.name,
                characterColor = character.color,
                characterPortrait = character.portrait,
                onClick = {
                    navController.navigate(
                        route = "characterDetail/${character.id}/${character.name}/" +
                                "${character.description}/${character.portrait}"
                    )
                }
            )
        }
    }
}

@Composable
fun SimpleCharacterCard(
    modifier: Modifier = Modifier,
    characterName: String,
    characterColor: String,
    characterPortrait: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .height(240.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = when (characterColor) {
                "red" -> LightRed
                "blue" -> LightBlue
                else -> LightGreen
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier.padding(
                    vertical = 8.dp,
                    horizontal = 10.dp
                ),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier
                        .heightIn(
                            min = 0.dp,
                            max = 150.dp
                        )
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = 3.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    Image(
                        painter = painterResource(
                            id = when (characterPortrait) {
                                "Mujer" -> R.drawable.ic_woman_icon
                                "Hombre" -> R.drawable.ic_man_icon
                                else -> R.drawable.ic_non_binary_icon
                            }
                        ),
                        contentDescription = stringResource(id = R.string.character_image_description)
                    )
                    Text(
                        text = characterName,
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 23.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 30.sp
                    )
                }
            }
        }
    }
}