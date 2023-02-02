package barrera.alejandro.librario.views.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.R
import barrera.alejandro.librario.models.routes.ScreenNavigation
import barrera.alejandro.librario.models.settings.entities.SettingsButtonData

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    landscapeOrientation: Boolean,
    onClickOption: (destinationScreen: ScreenNavigation?) -> Unit,
    paddingValues: PaddingValues
) {
    val settingsButtonsData = listOf(
        SettingsButtonData(
            buttonTextId = R.string.author_button_text,
            iconDrawableId = R.drawable.ic_author,
            iconDrawableDescriptionId = R.string.author_icon_description,
            destinationScreen = ScreenNavigation.AuthorScreen
        ),
        SettingsButtonData(
            buttonTextId = R.string.terms_and_conditions_button_text,
            iconDrawableId = R.drawable.ic_terms_and_conditions,
            iconDrawableDescriptionId = R.string.terms_and_conditions_icon_description,
            destinationScreen = ScreenNavigation.TermsAndConditionsScreen
        )
    )

    Column(
        modifier = if (landscapeOrientation) {
            modifier
                .padding(all = 20.dp)
                .padding(bottom = paddingValues.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        } else {
            modifier
                .padding(all = 20.dp)
                .padding(bottom = paddingValues.calculateBottomPadding())
                .fillMaxSize()
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Settings(
            settingsButtonsData = settingsButtonsData,
            onClickOption = onClickOption
        )
    }
}

@Composable
fun Settings(
    settingsButtonsData: List<SettingsButtonData>,
    onClickOption: (destinationScreen: ScreenNavigation?) -> Unit
) {
    settingsButtonsData.forEach { data ->
        SettingsButton(
            onClickOption = onClickOption,
            destinationScreen = data.destinationScreen,
            text = stringResource(id = data.buttonTextId),
            trailingIconPainter = painterResource(id = data.iconDrawableId),
            trailingIconContentDescription = stringResource(id = data.iconDrawableDescriptionId)
        )
    }
}