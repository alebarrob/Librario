package barrera.alejandro.librario.reading_journal.presentation.characters.add_character

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun AddCharacterScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navController: NavController
) {
    /*val context = LocalContext.current
    val addCharacterScreenViewModel = hiltViewModel<AddCharacterScreenViewModel>()

    val bookId by addCharacterScreenViewModel.bookId.collectAsState(initial = 0)
    var characterName by remember { mutableStateOf("") }
    var characterDescription by remember { mutableStateOf("") }
    var characterPortrait by remember { mutableStateOf("Mujer") }

    Column(
        modifier = if (landscapeOrientation) {
            modifier
                .padding(all = 20.dp)
                .padding(top = paddingValues.calculateTopPadding())
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        } else {
            modifier
                .padding(all = 20.dp)
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        DetailedCharacterCard(
            characterName = characterName,
            onCharacterNameChange = { characterName = it },
            characterDescription = characterDescription,
            onCharacterDescriptionChange = { characterDescription = it },
            characterPortrait = characterPortrait,
            onCharacterPortraitChange = { characterPortrait = it }
        )
        OptionButton(
            onClick = { *//*viewModel.onEvent(AddBookEvent.OnAddBookClick)*//* }
        ) {
            Text(
                text = stringResource(id = R.string.add_button_text),
                fontSize = 28.sp,
                style = MaterialTheme.typography.labelMedium
            )
        }
        *//*OptionButton(
            buttonTextId = R.string.accept_button_text,
            onClick = {
                if (characterName == "" || characterDescription == "") {
                    Toast.makeText(context, "¡No has completado la información del personaje!", Toast.LENGTH_LONG).show()
                } else {
                    addCharacterScreenViewModel.insertCharacter(
                        Character(
                            id = 0,
                            bookId = bookId,
                            name = characterName,
                            description = characterDescription,
                            portrait = characterPortrait
                        )
                    )
                    navController.popBackStack()
                    Toast.makeText(context, "El personaje fue añadido correctamente.", Toast.LENGTH_LONG).show()
                }
            }
        )*//*
    }*/
}