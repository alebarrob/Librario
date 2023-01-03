package barrera.alejandro.librario.models.entities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import barrera.alejandro.librario.models.routes.ScreenNavigation

data class SettingsCardData(
    @StringRes val buttonTextId: Int,
    @DrawableRes val iconDrawableId: Int,
    @StringRes val iconDrawableDescriptionId: Int,
    val destinationScreen: ScreenNavigation
)
