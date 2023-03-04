package barrera.alejandro.librario.core.domain.use_case

class SlashToDashConverter {
    operator fun invoke(text: String): String {
        return text.replace('/', '-')
    }
}