package barrera.alejandro.librario.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import barrera.alejandro.librario.R
import barrera.alejandro.librario.models.routes.ScreenNavigation

data class SettingsCardData(
    @StringRes val buttonTextId: Int,
    @DrawableRes val iconDrawableId: Int,
    @StringRes val iconDrawableDescriptionId: Int,
    val destinationScreen: ScreenNavigation
)

val settingsCardsData = listOf(
    SettingsCardData(
        buttonTextId = R.string.author_button_text,
        iconDrawableId = R.drawable.ic_author,
        iconDrawableDescriptionId = R.string.author_icon_description,
        destinationScreen = ScreenNavigation.AuthorScreen
    ),
    SettingsCardData(
        buttonTextId = R.string.terms_and_conditions_button_text,
        iconDrawableId = R.drawable.ic_terms_and_conditions,
        iconDrawableDescriptionId = R.string.terms_and_conditions_icon_description,
        destinationScreen = ScreenNavigation.TermsAndConditionsScreen
    )
)
