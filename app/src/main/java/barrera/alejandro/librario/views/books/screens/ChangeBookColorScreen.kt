package barrera.alejandro.librario.views.books.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barrera.alejandro.librario.viewmodels.books.ChangeBookColorScreenViewModel
import barrera.alejandro.librario.views.theme.LightBlue
import barrera.alejandro.librario.views.theme.LightGreen
import barrera.alejandro.librario.views.theme.LightRed

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

@Composable
fun ChangeColorButton(
    modifier: Modifier = Modifier,
    buttonColor: Color,
    onCLick: () -> Unit
) {
    Button(
        modifier = modifier.size(width = 80.dp, height = 60.dp),
        onClick = onCLick,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) { }
}