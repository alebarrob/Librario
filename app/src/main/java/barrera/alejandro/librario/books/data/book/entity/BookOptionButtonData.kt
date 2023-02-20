package barrera.alejandro.librario.books.data.book.entity

import androidx.annotation.StringRes

data class BookOptionButtonData(
    @StringRes val buttonTextId: Int,
    val onClick: () -> Unit
)
