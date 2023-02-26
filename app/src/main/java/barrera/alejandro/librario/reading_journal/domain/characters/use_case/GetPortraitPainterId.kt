package barrera.alejandro.librario.reading_journal.domain.characters.use_case

import barrera.alejandro.librario.R

class GetPortraitPainterId {
    operator fun invoke(portraitTag: String): Int {
        return when (portraitTag) {
            "Mujer" -> R.drawable.ic_woman_icon
            "Hombre" -> R.drawable.ic_man_icon
            else -> R.drawable.ic_non_binary_icon
        }
    }
}