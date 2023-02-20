package barrera.alejandro.librario.views.books.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barrera.alejandro.librario.R
import barrera.alejandro.librario.books.data.book.entity.BookOptionButtonData
import barrera.alejandro.librario.books.presentation.character.CharacterDetailScreenViewModel
import barrera.alejandro.librario.views.books.composables.BookOptions
import barrera.alejandro.librario.views.books.composables.DetailedCharacterCard
import barrera.alejandro.librario.core.presentation.components.AlertDialog

@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    landscapeOrientation: Boolean,
    navController: NavController,
    characterDetailScreenViewModel: CharacterDetailScreenViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    val context = LocalContext.current

    val characterId by characterDetailScreenViewModel.characterId.collectAsState(initial = 0)
    val characterName by characterDetailScreenViewModel.characterName.collectAsState(initial = "")
    val characterDescription by characterDetailScreenViewModel.characterDescription.collectAsState(initial = "")
    val characterColor by characterDetailScreenViewModel.characterColor.collectAsState(initial = "red")
    val characterPortrait by characterDetailScreenViewModel.characterPortrait.collectAsState(initial = "Mujer")

    var openDialog by rememberSaveable { mutableStateOf(false) }

    val bookOptionButtonsData = listOf(
        BookOptionButtonData(
            buttonTextId = R.string.save_changes_button_text,
            onClick = {
                if (characterName == "" || characterDescription == "") {
                    Toast.makeText(context, "¡No has completado la información del personaje!", Toast.LENGTH_LONG).show()
                } else {
                    characterDetailScreenViewModel.updateCharacter(
                        characterName, characterDescription, characterPortrait, characterId
                    )
                    navController.popBackStack()
                    Toast.makeText(context, "Los cambios se guardaron con éxito.", Toast.LENGTH_LONG).show()
                }
            },
        ),
        BookOptionButtonData(
            buttonTextId = R.string.change_color_button_text,
            onClick = {
                navController.navigate(route = "changeCharacterColor/${characterId}")
            }
        ),
        BookOptionButtonData(
            buttonTextId = R.string.delete_button_text,
            onClick = {
                openDialog = true
            }
        )
    )

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                openDialog = false
            },
            title = "¡Atención!",
            text = "Estás a punto de borrar este personaje. ¿Seguro que quieres continuar?",
            onClickConfirmButton = {
                openDialog = false
                navController.popBackStack()
                characterDetailScreenViewModel.deleteCharacter(characterId)
                Toast.makeText(context, "El personaje fue borrado con éxito.", Toast.LENGTH_LONG).show()
            },
            confirmButtonText = "Continuar",
            onClickDismissButton = {
                openDialog = false
            },
            dismissButtonText = "Cancelar",
        )
    }

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
        if (characterColor != null) {
            DetailedCharacterCard(
                characterName = characterName,
                onCharacterNameChange = { characterDetailScreenViewModel.onCharacterNameChange(it) },
                characterDescription = characterDescription,
                onCharacterDescriptionChange = { characterDetailScreenViewModel.onCharacterDescriptionChange(it) },
                characterPortrait = characterPortrait,
                onCharacterPortraitChange = { characterDetailScreenViewModel.onCharacterPortraitChange(it) },
                characterColor = characterColor
            )
            BookOptions(bookOptionButtonsData = bookOptionButtonsData)
        }
    }
}