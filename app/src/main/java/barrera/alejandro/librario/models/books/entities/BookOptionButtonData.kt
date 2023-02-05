package barrera.alejandro.librario.models.books.entities

import androidx.annotation.StringRes

data class BookOptionButtonData(
    @StringRes val buttonTextId: Int,
    val onClick: () -> Unit
)
