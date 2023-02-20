package barrera.alejandro.librario.views.books.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barrera.alejandro.librario.R
import barrera.alejandro.librario.books.data.character.entity.Character
import barrera.alejandro.librario.books.presentation.character.AddCharacterScreenViewModel
import barrera.alejandro.librario.views.books.composables.BookOptionButton
import barrera.alejandro.librario.views.books.composables.DetailedCharacterCard

@Composable
fun AddCharacterScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    landscapeOrientation: Boolean,
    navController: NavController
) {
    val context = LocalContext.current
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
            onCharacterPortraitChange = { characterPortrait = it },
            characterColor = "red"
        )
        BookOptionButton(
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
                            color = "red",
                            portrait = characterPortrait
                        )
                    )
                    navController.popBackStack()
                    Toast.makeText(context, "El personaje fue añadido correctamente.", Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}