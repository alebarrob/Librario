package barrera.alejandro.librario.models.routes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import barrera.alejandro.librario.R

sealed class ScreenNavigation(
    val route: String,
    @StringRes val iconLabelId: Int? = null,
    @DrawableRes val iconImageId: Int? = null,
    @StringRes val iconImageDescription: Int? = null
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
    object AuthorScreen : ScreenNavigation(route = "authorScreen")
    object TermsAndConditionsScreen : ScreenNavigation(route = "termsAndConditionsScreen")
}