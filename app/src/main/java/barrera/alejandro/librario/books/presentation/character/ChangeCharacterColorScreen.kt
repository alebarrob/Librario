package barrera.alejandro.librario.views.books.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barrera.alejandro.librario.books.presentation.character.ChangeCharacterColorScreenViewModel
import barrera.alejandro.librario.views.books.composables.ChangeColorButton
import barrera.alejandro.librario.core.presentation.theme.LightBlue
import barrera.alejandro.librario.core.presentation.theme.LightGreen
import barrera.alejandro.librario.core.presentation.theme.LightRed

@Composable
fun ChangeCharacterColorScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navController: NavController
) {
    val changeCharacterColorScreenViewModel = hiltViewModel<ChangeCharacterColorScreenViewModel>()

    val characterId by changeCharacterColorScreenViewModel.characterId.collectAsState(initial = 0)

    Row(
        modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ChangeColorButton(
            buttonColor = LightRed,
            onCLick = {
                changeCharacterColorScreenViewModel.changeColor(
                    characterColor = "red",
                    characterId = characterId
                )
                navController.popBackStack()
            }
        )
        ChangeColorButton(
            buttonColor = LightBlue,
            onCLick = {
                changeCharacterColorScreenViewModel.changeColor(
                    characterColor = "blue",
                    characterId = characterId
                )
                navController.popBackStack()
            }
        )
        ChangeColorButton(
            buttonColor = LightGreen,
            onCLick = {
                changeCharacterColorScreenViewModel.changeColor(
                    characterColor = "green",
                    characterId = characterId
                )
                navController.popBackStack()
            }
        )
    }
}