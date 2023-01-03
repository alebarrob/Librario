package barrera.alejandro.librario.views.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.R
import barrera.alejandro.librario.models.entities.SettingsCardData
import barrera.alejandro.librario.models.routes.ScreenNavigation

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onClickSettingsOption: (destinationScreen: ScreenNavigation) -> Unit,
    configuration: Configuration,
    paddingValues: PaddingValues
) {
    val settingsCardsData = listOf(
        SettingsCardData(
            buttonTextId = R.string.author_button_text,
            iconDrawableId = R.drawable.ic_author,
            iconDrawableDescriptionId = R.string.author_icon_description,
            destinationScreen = ScreenNavigation.AuthorScreen
        ),
        SettingsCardData(
            buttonTextId = R.string.terms_and_conditions_button_text,
            iconDrawableId = R.drawable.ic_terms_and_conditions,
            iconDrawableDescriptionId = R.string.terms_and_conditions_icon_description,
            destinationScreen = ScreenNavigation.TermsAndConditionsScreen
        )
    )

    Column(
        modifier = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> modifier
                .padding(all = 20.dp)
                .padding(bottom = paddingValues.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
            else -> modifier
                .padding(all = 20.dp)
                .padding(bottom = paddingValues.calculateBottomPadding())
                .fillMaxSize()
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        settingsCardsData.forEach { settingsCardData ->
            SettingsCard(
                onClickSettingsOption = onClickSettingsOption,
                destinationScreen = settingsCardData.destinationScreen,
                text = stringResource(id = settingsCardData.buttonTextId),
                trailingIconPainter = painterResource(id = settingsCardData.iconDrawableId),
                trailingIconContentDescription = stringResource(id = settingsCardData.iconDrawableDescriptionId)
            )
        }
    }
}
@Composable
fun SettingsCard(
    modifier: Modifier = Modifier,
    onClickSettingsOption: (screen: ScreenNavigation) -> Unit,
    destinationScreen: ScreenNavigation,
    text: String,
    trailingIconPainter: Painter,
    trailingIconContentDescription: String
) {
    Button(
        onClick = { onClickSettingsOption(destinationScreen) },
        shape = ShapeDefaults.Medium,
        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.secondary
        )
    ) {
        Row(
            modifier = modifier.padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                textAlign = TextAlign.Center,
                style = typography.labelLarge
            )
            Icon(
                painter = trailingIconPainter,
                contentDescription = trailingIconContentDescription
            )
        }
    }
}