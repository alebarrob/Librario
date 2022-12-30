package barrera.alejandro.salvaideas.models.routes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import barrera.alejandro.salvaideas.R

sealed class ScreenNavigation(
    val route: String,
    @StringRes val iconLabelId: Int,
    @DrawableRes val iconImageId: Int,
    @StringRes val iconImageDescription: Int
) {
    object BooksScreen : ScreenNavigation(
        route = "booksScreen",
        iconLabelId = R.string.books_label_text,
        iconImageId = R.drawable.ic_books,
        iconImageDescription = R.string.books_icon_description
    )
    object SettingsScreen : ScreenNavigation(
        route = "settingsScreen",
        iconLabelId = R.string.settings_label_text,
        iconImageId = R.drawable.ic_settings,
        iconImageDescription = R.string.settings_icon_description
    )
}