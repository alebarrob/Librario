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
    // Welcome Feature
    object WelcomeScreen : ScreenNavigation(route = "welcomeScreen")

    // Books Feature
    object BooksScreen : ScreenNavigation(
        route = "booksScreen",
        iconLabelId = R.string.books_label_text,
        iconImageId = R.drawable.ic_books,
        iconImageDescription = R.string.books_icon_description
    )
    object BookDetailScreen : ScreenNavigation(route = "bookDetailScreen/{bookTitle}/{bookAuthor}")
    object CharactersScreen : ScreenNavigation(route = "charactersScreen")
    object CharacterDetailScreen : ScreenNavigation(route = "characterDetailScreen")

    // Explore Feature
    object ExploreScreen : ScreenNavigation(
        route = "exploreScreen",
        iconLabelId = R.string.explore_label_text,
        iconImageId = R.drawable.ic_explore,
        iconImageDescription = R.string.explore_icon_description
    )

    // Settings Feature
    object SettingsScreen : ScreenNavigation(
        route = "settingsScreen",
        iconLabelId = R.string.settings_label_text,
        iconImageId = R.drawable.ic_settings,
        iconImageDescription = R.string.settings_icon_description
    )
    object AuthorScreen : ScreenNavigation(route = "authorScreen")
    object TermsAndConditionsScreen : ScreenNavigation(route = "termsAndConditionsScreen")
}