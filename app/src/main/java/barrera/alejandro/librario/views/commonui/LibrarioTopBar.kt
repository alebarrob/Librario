package barrera.alejandro.librario.views.commonui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import barrera.alejandro.librario.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibrarioTopBar(
    navController: NavController,
    topBarState: Boolean,
    backButtonState: Boolean,
    searchButtonState: Boolean
) {
    AnimatedVisibility(
        visible = topBarState,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it })
    ) {
        TopAppBar(
            title = {  },
            navigationIcon = {
                if (backButtonState) {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            stringResource(id = R.string.back_icon_description)
                        )
                    }
                }
            },
            actions = {
                if (searchButtonState) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = colorScheme.onPrimary)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = stringResource(id = R.string.search_icon_description)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorScheme.primary,
                navigationIconContentColor = colorScheme.onPrimary
            )
        )
    }
}