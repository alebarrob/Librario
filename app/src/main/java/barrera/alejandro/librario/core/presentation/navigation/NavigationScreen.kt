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
    object WelcomeScreen : NavigationScreen(route = "welcome")

    // Books Feature
    object BooksScreen : NavigationScreen(
        route = "books",
        iconLabelId = R.string.books_label_text,
        iconImageId = R.drawable.ic_books,
        iconImageDescription = R.string.books_icon_description
    )
    object AddBookScreen : NavigationScreen(route = "addBook")
    object BookDetailScreen : NavigationScreen(route = "bookDetail/{bookId}/{bookTitle}/{bookAuthor}/{bookDescription}/{bookColor}")
    object CharacterScreen : NavigationScreen(route = "characters/{bookId}")
    object CharacterDetailScreen : NavigationScreen(route = "characterDetail/{characterId}/{characterName}/{characterDescription}/{characterPortrait}")
    object ChangeBookColorScreen : NavigationScreen(route = "changeBookColor/{bookId}")
    object BookNotesScreen : NavigationScreen(route = "bookNotes/{bookId}/{bookNotes}")
    object AddCharacterScreen : NavigationScreen(route = "addCharacter/{bookId}")
    object ChangeCharacterColorScreen : NavigationScreen(route = "changeCharacterColor/{characterId}")

    // Explore Feature
    object ExploreScreen : NavigationScreen(
        route = "explore",
        iconLabelId = R.string.explore_label_text,
        iconImageId = R.drawable.ic_explore,
        iconImageDescription = R.string.explore_icon_description
    )

    // Settings Feature
    object SettingsScreen : NavigationScreen(
        route = "settings",
        iconLabelId = R.string.settings_label_text,
        iconImageId = R.drawable.ic_settings,
        iconImageDescription = R.string.settings_icon_description
    )
    object AuthorScreen : NavigationScreen(route = "author")
    object TermsAndConditionsScreen : NavigationScreen(route = "termsAndConditions")
}