package barrera.alejandro.librario.core.domain.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val notes: String = "Aquí puedes escribir tus notas sobre este libro."
)