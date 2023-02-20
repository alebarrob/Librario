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
import barrera.alejandro.librario.books.presentation.book.ChangeBookColorScreenViewModel
import barrera.alejandro.librario.views.books.composables.ChangeColorButton
import barrera.alejandro.librario.core.presentation.theme.LightBlue
import barrera.alejandro.librario.core.presentation.theme.LightGreen
import barrera.alejandro.librario.core.presentation.theme.LightRed

@Composable
fun ChangeBookColorScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navController: NavController
) {
    val changeBookColorScreenViewModel = hiltViewModel<ChangeBookColorScreenViewModel>()

    val bookId by changeBookColorScreenViewModel.bookId.collectAsState(initial = 0)

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
                changeBookColorScreenViewModel.changeColor(
                    bookColor = "red",
                    bookId = bookId
                )
                navController.popBackStack()
            }
        )
        ChangeColorButton(
            buttonColor = LightBlue,
            onCLick = {
                changeBookColorScreenViewModel.changeColor(
                    bookColor = "blue",
                    bookId = bookId
                )
                navController.popBackStack()
            }
        )
        ChangeColorButton(
            buttonColor = LightGreen,
            onCLick = {
                changeBookColorScreenViewModel.changeColor(
                    bookColor = "green",
                    bookId = bookId
                )
                navController.popBackStack()
            }
        )
    }
}

