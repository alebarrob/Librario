package barrera.alejandro.librario.core.data.mapper

import barrera.alejandro.librario.reading_journal.data.books.entity.BookEntity
import barrera.alejandro.librario.core.domain.model.Book
import barrera.alejandro.librario.explore.data.dto.VolumeInfo

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        author = author,
        description = description,
        notes = notes
    )
}

fun Book.toVolumeInfo(): VolumeInfo {
    return VolumeInfo(
        title = title,
        authors = listOf(author),
        description = description
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        author = author,
        description = description,
        notes = notes
    )
}

fun VolumeInfo.toBook(): Book {
    return Book(
        id = 0,
        title = title ?: "Título no disponible",
        author = authors?.joinToString(",") ?: "Autor no disponible",
        description = description ?: "Descripción no disponible"
    )
}