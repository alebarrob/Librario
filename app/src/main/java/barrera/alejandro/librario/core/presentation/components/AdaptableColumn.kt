package barrera.alejandro.librario.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import barrera.alejandro.librario.core.presentation.theme.LocalSpacing

@Composable
fun AdaptableColumn(
    modifier: Modifier = Modifier,
    landscapeOrientation: Boolean,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
    bottomBarPadding: Dp = 0.dp,
    topBarPadding: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = if (landscapeOrientation) {
            modifier
                .padding(all = spacing.spaceMedium)
                .padding(
                    top = topBarPadding,
                    bottom = bottomBarPadding
                )
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        } else {
            modifier
                .padding(all = spacing.spaceMedium)
                .padding(
                    top = topBarPadding,
                    bottom = bottomBarPadding
                )
                .fillMaxSize()
        },
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
        ) {
            content()
        }
}