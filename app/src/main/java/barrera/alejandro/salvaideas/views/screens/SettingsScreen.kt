package barrera.alejandro.salvaideas.views.screens

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import barrera.alejandro.salvaideas.R

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    configuration: Configuration,
    paddingValues: PaddingValues
) {
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
        SettingsCard(
            settingsLabel = stringResource(id = R.string.author_button_text),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_author),
                    contentDescription = stringResource(id = R.string.author_icon_description)
                )
            }
        )
        SettingsCard(
            settingsLabel = stringResource(id = R.string.terms_and_conditions_button_text),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_terms_and_conditions),
                    contentDescription = stringResource(id = R.string.terms_and_conditions_icon_description)
                )
            }
        )
    }
}
@Composable
fun SettingsCard(
    modifier: Modifier = Modifier,
    settingsLabel: String,
    leadingIcon:  @Composable (() -> Unit)? = null,
    trailingIcon:  @Composable (() -> Unit)? = null
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = {  },
        shape = ShapeDefaults.Medium,
        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.secondary
        ),
        contentPadding = PaddingValues(vertical = 25.dp, horizontal = 25.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.invoke()
            Text(
                text = settingsLabel,
                textAlign = TextAlign.Center,
                style = typography.labelLarge
            )
            trailingIcon?.invoke()
        }
    }
}