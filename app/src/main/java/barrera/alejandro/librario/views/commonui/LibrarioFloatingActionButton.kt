package barrera.alejandro.librario.views.commonui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import barrera.alejandro.librario.R

@Composable
fun LibrarioFloatingActionButton(
    onClickFloatingActionButton: () -> Unit,
    floatingActionButtonState: MutableState<Boolean>,
) {
    AnimatedVisibility(
        visible = floatingActionButtonState.value,
        enter = slideInHorizontally(initialOffsetX = { it }),
        exit = slideOutHorizontally(targetOffsetX = { it }),
    ) {
        FloatingActionButton(
            onClick = { onClickFloatingActionButton() },
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