package barrera.alejandro.librario.views.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TermsAndConditionsScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    context: Context
) {
    val termsAndConditionsInputStream = context.assets.open("text/terms_and_conditions.txt")
    val termsAndConditions = termsAndConditionsInputStream.bufferedReader().use { it.readText() }

    Column(
        modifier
            .padding(all = 15.dp)
            .padding(top = paddingValues.calculateTopPadding())
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = termsAndConditions)
    }
}