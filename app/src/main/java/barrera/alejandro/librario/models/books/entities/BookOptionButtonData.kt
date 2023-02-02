package barrera.alejandro.librario.models.books.entities

import androidx.annotation.StringRes
import barrera.alejandro.librario.models.routes.ScreenNavigation

data class BookOptionButtonData(
    @StringRes val buttonTextId: Int,
    val destinationScreen: ScreenNavigation
)
