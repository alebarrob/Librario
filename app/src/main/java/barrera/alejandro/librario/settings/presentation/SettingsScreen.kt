package barrera.alejandro.librario.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import barrera.alejandro.librario.R
import barrera.alejandro.librario.core.presentation.components.AdaptableColumn
import barrera.alejandro.librario.core.presentation.navigation.NavigationScreen.AuthorScreen
import barrera.alejandro.librario.core.presentation.navigation.NavigationScreen.TermsAndConditionsScreen
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing
import barrera.alejandro.librario.settings.presentation.components.SettingsButton

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onNavigate: (route: String) -> Unit,
) {
    val spacing = LocalSpacing.current

    AdaptableColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = spacing.spaceMedium,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        bottomBarPadding = paddingValues.calculateBottomPadding()
    ) {
        SettingsButton(
            onClick = { onNavigate(AuthorScreen.route) },
            text = stringResource(id = R.string.author_button_text),
            trailingIconPainter = painterResource(id = R.drawable.ic_author)
        )
        SettingsButton(
            onClick = { onNavigate(TermsAndConditionsScreen.route) },
            text = stringResource(id = R.string.terms_and_conditions_button_text),
            trailingIconPainter = painterResource(id = R.drawable.ic_terms_and_conditions)
        )

    }
}