package barrera.alejandro.librario.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import barrera.alejandro.librario.R

@Composable
fun FloatingButton(
    onClickAddBookButton: () -> Unit,
    addBookButtonState: Boolean,
) {
    AnimatedVisibility(
        visible = addBookButtonState,
        enter = slideInHorizontally(initialOffsetX = { it * 2 }),
        exit = slideOutHorizontally(targetOffsetX = { it * 2 }),
    ) {
        FloatingActionButton(
            onClick = { onClickAddBookButton() },
            shape = CircleShape,
            containerColor = colorScheme.primary,
            contentColor = colorScheme.onPrimary
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_floating_action_button),
                contentDescription = stringResource(id = R.string.floating_action_button_icon_description)
            )
        }
    }
}