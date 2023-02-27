package barrera.alejandro.librario.core.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import barrera.alejandro.librario.R

sealed class NavigationScreen(
    val route: String,
    @StringRes val iconLabelId: Int? = null,
    @DrawableRes val iconImageId: Int? = null,
    @StringRes val iconImageDescription: Int? = null
) {
    // Welcome Feature
    object WelcomeScreen: NavigationScreen(route = "welcome")

    // Books Feature
    object BooksOverviewScreen: NavigationScreen(
        route = "books",
        iconLabelId = R.string.books_label_text,
        iconImageId = R.drawable.ic_books,
        iconImageDescription = R.string.books_icon_description
    )
    object AddBookScreen: NavigationScreen(route = "addBook")
    object BookDetailScreen: NavigationScreen(route = "bookDetail")
    object CharacterScreen: NavigationScreen(route = "characters")
    object CharacterDetailScreen: NavigationScreen(route = "characterDetail")
    object BookNotesScreen: NavigationScreen(route = "bookNotes")
    object AddCharacterScreen: NavigationScreen(route = "addCharacter")

    // Explore Feature
    object ExploreBooksScreen: NavigationScreen(
        route = "exploreBooks",
        iconLabelId = R.string.explore_label_text,
        iconImageId = R.drawable.ic_explore,
        iconImageDescription = R.string.explore_icon_description
    )
    object ExploreBookDetailScreen: NavigationScreen(route = "exploreBookDetail")

    // Settings Feature
    object SettingsScreen: NavigationScreen(
        route = "settings",
        iconLabelId = R.string.settings_label_text,
        iconImageId = R.drawable.ic_settings,
        iconImageDescription = R.string.settings_icon_description
    )
    object AuthorScreen: NavigationScreen(route = "author")
    object TermsAndConditionsScreen : NavigationScreen(route = "termsAndConditions")
}